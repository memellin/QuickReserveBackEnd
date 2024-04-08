package com.example.demo.dto.school;

import com.example.demo.domain.school.School;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SchoolResponseDTO {
    SchoolDetailDTO schoolDetailDTO;

    public SchoolResponseDTO(School school, Integer numberOfAttendees) {
        this.schoolDetailDTO = new SchoolDetailDTO(school.getId(), school.getName(), school.getDetails(), school.getSlug(), school.getMaximumAttendees(), numberOfAttendees);
    }
}
