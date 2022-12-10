package com.example.demo.repository;

import com.example.demo.controller.CatFactController;
import com.example.demo.repository.entities.CatFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatFactRepository extends JpaRepository<CatFact, Long> {
}
