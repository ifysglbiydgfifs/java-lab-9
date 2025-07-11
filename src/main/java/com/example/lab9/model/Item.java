package com.example.lab9.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;


@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private boolean purchased;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public Boolean isPurchased() {
        return purchased;
    }
}
