package com.example.demo.services;

import com.example.demo.repos.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public void getPeopleDetail(){

        return;
    }
}
