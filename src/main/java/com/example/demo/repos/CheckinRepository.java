package com.example.demo.repos;

import com.example.demo.domain.checkin.Checkin;
import com.example.demo.domain.school.School;
import org.hibernate.annotations.Check;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<Checkin, Integer> {
}
