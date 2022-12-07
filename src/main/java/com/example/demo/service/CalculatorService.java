package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    int sum = 0;

    // sets sum to 0
    public void resetSum() {
        sum = 0;
        System.out.println("sum reset");
    }

    // adds input to sum and returns
    public int currentSum(int n) {
        sum += n;
        return sum;
    }
}
