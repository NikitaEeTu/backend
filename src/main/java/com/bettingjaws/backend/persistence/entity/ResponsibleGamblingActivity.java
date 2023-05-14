package com.bettingjaws.backend.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "responsible_gaming_activity_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsibleGamblingActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String activity_name;
    private String activity_description;
    private String creator_id;
    private Integer total_xp_number;
    private String access_level_id;
}
