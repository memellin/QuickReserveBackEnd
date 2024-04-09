package com.example.demo.repos;

import com.example.demo.domain.people.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository extends JpaRepository<People, String> {
    List<People> findBySchoolId(String schoolId);
    Optional<People> findBySchoolIdAndEmail(String schoolId, String email);
}
