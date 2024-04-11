package com.example.demo.controllers;

import com.example.demo.dto.people.PeopleBadgeResponseDTO;
import com.example.demo.services.CheckInService;
import com.example.demo.services.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/peoples")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;


    @GetMapping("/{peopleId}/badge") //perfil = badge
    public ResponseEntity<PeopleBadgeResponseDTO> getTest(@PathVariable String peopleId, UriComponentsBuilder uriBuilder){
        PeopleBadgeResponseDTO responseDTO = this.peopleService.getPeopleBadge(peopleId, uriBuilder);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/{peopleId}/check-in")
    public ResponseEntity registerCheckIn(@PathVariable String peopleId, UriComponentsBuilder uriBuilder){
        this.peopleService.checkInPeople(peopleId);
        var uri = uriBuilder.path("/peoples/{peopleId}/badge").buildAndExpand(peopleId).toUri();
        return ResponseEntity.created(uri).build();
    }
}
