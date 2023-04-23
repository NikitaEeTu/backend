package com.bettingjaws.backend.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "specialist_table")
public class Specialist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String clinic_name;
    private String country;
    private String website;
    @Enumerated(EnumType.STRING)
    private SpecialistType type;

}
