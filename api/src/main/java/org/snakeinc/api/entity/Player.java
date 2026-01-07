package org.snakeinc.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @NoArgsConstructor(force = true)
public class Player {
    @Getter @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 1;
    @Getter
    private final String name;
    @Getter
    private final int age;
    @Getter
    private final String category;
    @Getter
    private final LocalDateTime created_at;

    public Player(String name, int age){
        this.name = name;
        this.age = age;
        if (age <= 18){
            this.category = "Junior";
        } else {
            this.category = "Senior";
        }
        this.created_at = LocalDateTime.now();
    }
}
