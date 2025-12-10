package org.snakeinc.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @NoArgsConstructor(force = true)
public class Player {
    @Getter @Id
    private final int id;
    @Getter
    private final String name;
    @Getter
    private final int age;
    @Getter
    private final String category;
    @Getter
    private final LocalDateTime created_at;
    private static int id_count = 1;

    public Player(String name, int age){
        this.id = id_count;
        this.name = name;
        this.age = age;
        if (age <= 18){
            this.category = "Junior";
        } else {
            this.category = "Senior";
        }
        this.created_at = LocalDateTime.now();
        ++id_count;

    }
}
