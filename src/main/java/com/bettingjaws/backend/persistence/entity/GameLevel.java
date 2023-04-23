package com.bettingjaws.backend.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game_level_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer level_number;
    private Integer xp_required;
}
