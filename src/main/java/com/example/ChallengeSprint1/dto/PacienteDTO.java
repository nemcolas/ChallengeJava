package com.example.ChallengeSprint1.dto;

import lombok.Data;

@Data
public class PacienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private Long genero;
    private Long endereco;

}
