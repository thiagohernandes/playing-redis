package com.playing.api.controller;

import com.playing.api.domain.PersonDto;
import com.playing.api.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PersonDto>> getAll() throws InterruptedException {
        return ResponseEntity.ok(personService.getAll());
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonDto> getByCode(@PathVariable Integer code)
        throws InterruptedException {
        return ResponseEntity.ok(personService.getByCode(code));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonDto> save(@RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personService.save(personDto));
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer code) {
        personService.deleteByCode(code);
    }

}
