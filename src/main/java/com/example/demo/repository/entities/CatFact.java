package com.example.demo.repository.entities;

import javax.persistence.*;

@Entity(name="CATFACT")
public class CatFact {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(length=1000)
    private String fact;
    private int length;

    public CatFact() {
    }

    public CatFact(String fact, int length) {
        this.fact = fact;
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
