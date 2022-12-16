package com.example.demo.service;

import com.example.demo.repository.PersonnelRepository;
import com.example.demo.repository.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    public List<Person> list() {
        return personnelRepository.findAll();
    }

    public String add(Person person) {
        // todo fix broken matching
        if (!personnelRepository.exists(Example.of(person)))
            personnelRepository.save(person);
        return "added";
    }

    public String remove(Person person) {
        long count = personnelRepository.deleteByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        return "removed: " + count;
    }
}
