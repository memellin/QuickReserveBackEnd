package com.example.demo.services;

import com.example.demo.domain.people.People;
import com.example.demo.domain.school.School;
import com.example.demo.domain.school.exceptions.SchoolFullException;
import com.example.demo.domain.school.exceptions.SchoolNotFoundException;
import com.example.demo.dto.school.SchoolIdDTO;
import com.example.demo.dto.school.SchoolRequestDTO;
import com.example.demo.dto.school.SchoolResponseDTO;
import com.example.demo.repos.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final PeopleService peopleService;

    public SchoolResponseDTO getSchoolDetail(String schoolId){
        School school = this.schoolRepository.findById(schoolId).orElseThrow(() -> new SchoolNotFoundException(schoolId));
        List<People> peopleList = this.peopleService.getAllPeople(schoolId);
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

    public void registerPeopleOnSchool(String schoolId){
        this.peopleService.verifyPeopleSubscription("", schoolId);

        School school = this.schoolRepository.findById(schoolId).orElseThrow(() -> new SchoolNotFoundException(schoolId));
        List<People> peopleList = this.peopleService.getAllPeople(schoolId);

        if(school.getMaximumAttendees() < peopleList.size()){
            throw new SchoolFullException("Maximum people reached");
        }
    }


    private String createSlug(String txt){
        //normalizacao para separar acentos de letras
        String normalized = Normalizer.normalize(txt, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();

    }

}
