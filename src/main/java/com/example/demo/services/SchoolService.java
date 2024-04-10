package com.example.demo.services;

import com.example.demo.domain.people.People;
import com.example.demo.domain.school.School;
import com.example.demo.domain.school.exceptions.SchoolFullException;
import com.example.demo.domain.school.exceptions.SchoolNotFoundException;
import com.example.demo.dto.people.PeopleIdDTO;
import com.example.demo.dto.people.PeopleRequestDTO;
import com.example.demo.dto.school.SchoolIdDTO;
import com.example.demo.dto.school.SchoolRequestDTO;
import com.example.demo.dto.school.SchoolResponseDTO;
import com.example.demo.repos.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final PeopleService peopleService;

    public SchoolResponseDTO getSchoolDetail(String schoolId){
        School school = this.getSchoolById(schoolId);
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

    public PeopleIdDTO registerPeopleOnSchool(String schoolId, PeopleRequestDTO peopleRequestDTO){
        this.peopleService.verifyPeopleSubscription(peopleRequestDTO.email(), schoolId);

        School school = this.getSchoolById(schoolId);
        List<People> peopleList = this.peopleService.getAllPeople(schoolId);

        if(school.getMaximumAttendees() < peopleList.size()){
            throw new SchoolFullException("Maximum people reached");
        }

        People newPeople = new People();
        newPeople.setName(peopleRequestDTO.name());
        newPeople.setEmail(peopleRequestDTO.email());
        newPeople.setSchool(school);
        newPeople.setCreatedAt(LocalDateTime.now());
        return new PeopleIdDTO(newPeople.getId());
    }

    private School getSchoolById(String schoolId){
        return this.schoolRepository.findById(schoolId).orElseThrow(() -> new SchoolNotFoundException(schoolId));
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
