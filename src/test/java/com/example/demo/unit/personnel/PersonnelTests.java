package com.example.demo.unit.personnel;

import com.example.demo.repository.entities.Person;
import com.example.demo.service.PersonnelService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonnelTests {

    @Autowired
    private PersonnelService personnelService;

    @Test
    public void whenApplicationStarts_hibernateCreatesInitialRecords() {
        List<Person> personnel = personnelService.list();

        Assert.assertEquals(personnel.size(), 3);
    }
}
