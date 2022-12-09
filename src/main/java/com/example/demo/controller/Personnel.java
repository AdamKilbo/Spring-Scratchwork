package com.example.demo.controller;

import com.example.demo.repository.entities.Person;
import com.example.demo.service.CalculatorService;
import com.example.demo.service.PersonnelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personnel")
public class Personnel {
    private final PersonnelService ps = new PersonnelService();

    @RequestMapping("/list")
    @ResponseBody
    public List<Person> listPersonnel() {
        return ps.list();
    }
}
