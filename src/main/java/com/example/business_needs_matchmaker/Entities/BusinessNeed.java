package com.example.business_needs_matchmaker.Entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class BusinessNeed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY)
    private Long id;
    private String description;
    @ElementCollection
    private List<String> keywords;


    public BusinessNeed(Long id,String description,List<String> keywords) {
        this.id = id;
        this.description = description;
        this.keywords = keywords;
    }

    public BusinessNeed() {

    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
