package com.example.demo.dto.school;

public record SchoolDetailDTO(
        String id,
        String name,
        String details,
        String slug,
        Integer maximumAttendees,
        Integer attendeesAmount
) {

    }
