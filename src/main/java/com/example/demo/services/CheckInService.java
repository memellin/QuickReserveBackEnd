package com.example.demo.services;

import com.example.demo.domain.checkin.Checkin;
import com.example.demo.domain.checkin.exceptions.CheckedInAlreadyExistsExceptions;
import com.example.demo.domain.people.People;
import com.example.demo.repos.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {

    private final CheckinRepository checkinRepository;

    public void checkInPeople(People people){
        this.verifyCheckInExists(people.getId());
        Checkin newCheckin = new Checkin();
        newCheckin.setPeople(people);
        newCheckin.setCreatedAt(LocalDateTime.now());
        this.checkinRepository.save(newCheckin);
    }
    private void verifyCheckInExists(String peopleId) {
        Optional<Checkin> isCheckedIn = this.findCheckinById(peopleId);
        if(isCheckedIn.isPresent()){
            throw new CheckedInAlreadyExistsExceptions("Pessoa ja checkou");
        }
    }

    public Optional<Checkin> findCheckinById(String peopleId){
        return this.checkinRepository.findByPeopleId(peopleId);

    }
}
