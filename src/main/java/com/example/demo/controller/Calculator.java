package com.example.demo.controller;

import com.example.demo.service.CalculatorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Calculator {
    private final CalculatorService cs = new CalculatorService();

    @RequestMapping("/add")
    @ResponseBody
    public int addSum(int n) {
        return cs.currentSum(n);
    }

    @RequestMapping("/reset")
    @ResponseBody
    public String resetSum() {
        cs.resetSum();
        return "reset";
    }
}
