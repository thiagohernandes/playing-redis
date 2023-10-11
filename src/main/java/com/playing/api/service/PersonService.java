package com.playing.api.service;

import com.playing.api.domain.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {

    private final List<PersonDto> listPerson = new ArrayList<>();

    public PersonService() {
        for(int x = 1; x < 20; x++) {
            this.listPerson.add(PersonDto.builder()
                    .code(x)
                    .name("Name ".concat(String.valueOf(x)))
                .build());
        }
    }

    @Cacheable(value = "person")
    public List<PersonDto> getAll() throws InterruptedException {
        Thread.sleep(5000);

        return listPerson.stream()
            .sorted(Comparator.comparing(PersonDto::getCode))
            .collect(Collectors.toList());
    }

    @Cacheable(value = "person", key = "#code")
    public PersonDto getByCode(final Integer code) throws InterruptedException {
        return listPerson.stream()
            .filter(person -> person.getCode().equals(code))
            .findFirst()
            .orElse(PersonDto.builder().build());
    }

    @CacheEvict(value = "person", allEntries = true)
    public PersonDto save(final PersonDto personDto) {
        final var lastPersonCode = listPerson.stream()
            .sorted(Comparator.comparing(PersonDto::getCode))
            .toList()
            .stream()
            .reduce((first, second) -> second)
            .get().getCode();

        personDto.setCode(lastPersonCode + 1);
        personDto.setName(personDto.getName());
        listPerson.add(personDto);

        return personDto;
    }

    @CacheEvict(value = "person")
    public void deleteByCode(final Integer code) {
        listPerson.removeIf(person -> person.getCode().equals(code));
    }
}
