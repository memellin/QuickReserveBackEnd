package com.example.demo.repos;

import com.example.demo.domain.checkin.Checkin;
import com.example.demo.domain.school.School;
import org.hibernate.annotations.Check;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckinRepository extends JpaRepository<Checkin, Integer> {
    Optional<Checkin> findByPeopleId(String peopleId);
}
