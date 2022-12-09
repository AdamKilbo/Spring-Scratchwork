package com.example.demo.repository;

import com.example.demo.repository.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepository extends JpaRepository<Person, Long> {
}
