package com.example.demo.services;

import com.example.demo.domain.people.People;
import com.example.demo.domain.school.School;
import com.example.demo.dto.school.SchoolResponseDTO;
import com.example.demo.repos.PeopleRepository;
import com.example.demo.repos.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final PeopleRepository peopleRepository;

    public SchoolResponseDTO getSchoolDetail(String schoolId){
        School school = this.schoolRepository.findById(schoolId).orElseThrow(() -> new RuntimeException("Escola nao encontrada com id: " + schoolId));
        List<People> peopleList = this.peopleRepository.findBySchoolId(schoolId);
        return new SchoolResponseDTO(school, peopleList.size());
    }

}
