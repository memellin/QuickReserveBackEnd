package com.example.demo.services;

import com.example.demo.domain.people.People;
import com.example.demo.domain.school.School;
import com.example.demo.dto.school.SchoolIdDTO;
import com.example.demo.dto.school.SchoolRequestDTO;
import com.example.demo.dto.school.SchoolResponseDTO;
import com.example.demo.repos.PeopleRepository;
import com.example.demo.repos.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
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

    public SchoolIdDTO createSchool(SchoolRequestDTO schoolDto){
        School school = new School();
        school.setName(schoolDto.name());
        school.setDetails(schoolDto.details());
        school.setMaximumAttendees(schoolDto.maximumAttendees());
        school.setSlug(this.createSlug(schoolDto.name()));
        this.schoolRepository.save(school);
        return  new SchoolIdDTO(school.getId());
    }

    private String createSlug(String txt){
        //normalizacao para separa acentos de letras
        String normalized = Normalizer.normalize(txt, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();

    }

}
