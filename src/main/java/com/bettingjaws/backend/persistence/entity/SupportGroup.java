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
@Table(name = "support_group_table")
public class SupportGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String group_name;
    private String city;
    private String country;
    @Enumerated(EnumType.STRING)
    private SupportGroupType type;
    private UUID creator_id;
    private String website;

}
