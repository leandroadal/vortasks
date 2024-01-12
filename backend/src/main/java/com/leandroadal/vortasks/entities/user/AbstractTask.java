package com.leandroadal.vortasks.entities.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public abstract class AbstractTask {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String name; 
    private String description; 
    private String type; // Lazer ou atividade 
    private float xp; 
    private float reward; // moeda
    private int skillIncrease; // Aumentar xp da skill 
    private int skillDecrease;
}
