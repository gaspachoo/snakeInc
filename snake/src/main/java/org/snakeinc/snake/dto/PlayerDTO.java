package org.snakeinc.snake.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private int id;
    private String name;
    private int age;
    private String category;

    @Override
    public String toString() {
        return name + " (" + category + ")";
    }
}

