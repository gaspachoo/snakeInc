package org.snakeinc.api.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PlayerParams {
    @NotBlank(message = "Il faut saisir un nom !")
    private String name;
    @Min(value = 13, message= "Va jouer aux Playmobil fumier ")
    private int age;
}
