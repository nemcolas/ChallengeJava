package com.example.ChallengeSprint1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DentistaDTO {

    @NotNull(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome não pode ter mais que 100 caracteres")
    private String nome;

    @NotNull(message = "O cro é obrigatório")
    @Size(max = 15, message = "O cro não pode ter mais que 15 caracteres")
    private String cro;

    @NotNull(message = "A especialidade é obrigatória")
    @Size(max = 50, message = "A especialidade não pode ter mais que 50 caracteres")
    private String especialidade;

    @NotNull(message = "O id do gênero é obrigatório")
    private Long genero;

    @NotNull(message = "O id do endereço é obrigatório")
    private Long endereco;

}

