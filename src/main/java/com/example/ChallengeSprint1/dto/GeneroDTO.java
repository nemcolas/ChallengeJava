package com.example.ChallengeSprint1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GeneroDTO {

    private Long id;

    @NotNull(message = "A descrição é obrigatória")
    @Size(max = 20, message = "A descrição não pode ter mais que 20 caracteres")
    private String descricao;

}
