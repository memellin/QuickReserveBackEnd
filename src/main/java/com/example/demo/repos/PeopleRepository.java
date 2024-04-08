package com.example.demo.repos;

import com.example.demo.domain.people.People;
import com.example.demo.domain.school.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, String> {
    List<People> findBySchoolId(String schoolId);
}
