package com.example.demo.dto.people;

import java.time.LocalDateTime;

public record PeopleDetail (String id, String name, String email, LocalDateTime createdAt, LocalDateTime checkedInAt) {
}
