package com.example.demo.controller;

import com.example.demo.service.CatFactService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatFact {

    @RequestMapping("/fact")
    @ResponseBody
    public String getCatFact() {
        CatFactService catFactService = new CatFactService();
        return catFactService.getCatFact();
    }
}

