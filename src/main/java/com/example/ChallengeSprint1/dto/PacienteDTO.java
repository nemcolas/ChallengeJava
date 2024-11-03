package com.example.ChallengeSprint1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PacienteDTO {

    @NotNull(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome não pode ter mais que 100 caracteres")
    private String nome;

    @NotNull(message = "O cpf é obrigatório")
    @Size(max = 11, message = "O cpf não pode ter mais que 11 caracteres")
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Size(max = 20, message = "A data de nascimento não pode ter mais que 20 caracteres")
    private String dataNascimento;

    @NotNull(message = "O id do gênero é obrigatório")
    private Long genero;

    @NotNull(message = "O id do endereço é obrigatório")
    private Long endereco;

}
