package com.example.demo.services;

import com.example.demo.domain.checkin.Checkin;
import com.example.demo.domain.people.People;
import com.example.demo.domain.people.exceptions.PeopleNotFoundException;
import com.example.demo.domain.people.exceptions.PeopleAlreadyRegisteredException;
import com.example.demo.dto.people.PeopleBadgeDTO;
import com.example.demo.dto.people.PeopleBadgeResponseDTO;
import com.example.demo.dto.people.PeopleDetail;
import com.example.demo.dto.people.PeopleListResponseDTO;
import com.example.demo.repos.CheckinRepository;
import com.example.demo.repos.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final CheckInService checkInService;

    public List<People> getAllPeople(String schoolId){
        return this.peopleRepository.findBySchoolId(schoolId);
    }

    public PeopleListResponseDTO getSchoolsPeople(String schoolId){
        List<People> peopleList = this.getAllPeople(schoolId);
        List<PeopleDetail> peopleDetailList = peopleList.stream().map(people -> {
            Optional<Checkin> checkIn = this.checkInService.findCheckinById(people.getId());
            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(Checkin::getCreatedAt).orElse(null);
            return new PeopleDetail(people.getId(), people.getName(), people.getEmail(), people.getCreatedAt(), checkedInAt);
        }).toList();
        return new PeopleListResponseDTO(peopleDetailList);
    }

    public void verifyPeopleSubscription(String email, String schoolId){
            Optional<People> isPeopleRegistered = this.peopleRepository.findBySchoolIdAndEmail(email, schoolId);
            if(isPeopleRegistered.isPresent()){
                throw new PeopleAlreadyRegisteredException("People is already registered!");
            }
    }

    public People registerNewPeople(People people){
        this.peopleRepository.save(people);
        return people;
    }

    public void checkInPeople(String peopleId){
        People people = this.getPeople(peopleId);
        this.checkInService.checkInPeople(people);
// nao retorna nada, só a verificacao de checkin
    }

    private People getPeople(String peopleId){
        return this.peopleRepository.findById(peopleId).orElseThrow(() -> new PeopleNotFoundException("pessoa nao encontrada com o id" + peopleId));
    }

//badge eh o cracha
    public PeopleBadgeResponseDTO getPeopleBadge(String peopleId, UriComponentsBuilder uriComponentsBuilder){
        People people = this.getPeople(peopleId);
        var uri = uriComponentsBuilder.path("/peoples/{peopleId}/check-in").buildAndExpand(peopleId).toUri().toString();
        PeopleBadgeDTO peopleBadgeDTO = new PeopleBadgeDTO(
                people.getName(),
                people.getEmail(),
                uri,
                people.getSchool().getId());
        return new PeopleBadgeResponseDTO(peopleBadgeDTO);
    }
}
