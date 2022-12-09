package com.example.demo.service;

import com.example.demo.repository.PersonnelRepository;
import com.example.demo.repository.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    public List<Person> list() {
        return personnelRepository.findAll();
    }
}
