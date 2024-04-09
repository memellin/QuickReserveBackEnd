package com.example.demo.services;

import com.example.demo.domain.checkin.Checkin;
import com.example.demo.domain.people.People;
import com.example.demo.domain.people.exceptions.PeopleAlreadyRegisteredException;
import com.example.demo.dto.people.PeopleDetail;
import com.example.demo.dto.people.PeopleListResponseDTO;
import com.example.demo.repos.CheckinRepository;
import com.example.demo.repos.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final CheckinRepository checkInRepository;

    public List<People> getAllPeople(String schoolId){
        return this.peopleRepository.findBySchoolId(schoolId);
    }

    public PeopleListResponseDTO getSchoolsPeople(String schoolId){
        List<People> peopleList = this.getAllPeople(schoolId);
        List<PeopleDetail> peopleDetailList = peopleList.stream().map(people -> {
            Optional<Checkin> checkIn = this.checkInRepository.findByPeopleId(people.getId());
            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(Checkin::getCreatedAt).orElse(null);
            return new PeopleDetail(people.getId(), people.getName(), people.getEmail(), people.getCreatedAt(), checkedInAt);
        }).toList();
        return new PeopleListResponseDTO(peopleDetailList);
    }

    public void verifyPeopleSubscription( String email, String schoolId){
            Optional<People> isPeopleRegistered = this.peopleRepository.findBySchoolIdAndEmail(email, schoolId);
            if(isPeopleRegistered.isPresent()){
                throw new PeopleAlreadyRegisteredException("People is already registered!");
            }


    }

    public People registerNewPeople(People people){
        return this.peopleRepository.save(people);
    }
}
