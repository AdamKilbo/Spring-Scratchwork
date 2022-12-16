package com.example.demo.repository;

import com.example.demo.repository.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PersonnelRepository extends JpaRepository<Person, Long> {
    @Transactional
    long deleteByFirstNameAndLastName(String firstName, String lastName);
}
