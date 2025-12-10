package org.snakeinc.api.entity;

import lombok.Getter;

import java.time.LocalDateTime;
public class Player {
    @Getter
    private final int id;
    @Getter
    private String name;
    @Getter
    private int age;
    @Getter
    private String category;
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
