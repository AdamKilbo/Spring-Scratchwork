package com.example.demo.controller;

import com.example.demo.repository.entities.CatFact;
import com.example.demo.service.CatFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatFactController {
    @Autowired
    CatFactService catFactService = new CatFactService();

    @RequestMapping("/fact")
    @ResponseBody
    public CatFact getCatFact() {
        return catFactService.getCatFact();
    }
}

