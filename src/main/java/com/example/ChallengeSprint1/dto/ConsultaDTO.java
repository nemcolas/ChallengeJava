package com.example.ChallengeSprint1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ConsultaDTO {

    private Long id;

    @NotNull(message = "A data da consulta é obrigatória")
    @Size(max = 20, message = "A data da consulta não pode ter mais que 20 caracteres")
    private String dataConsulta;

    @NotNull(message = "O tipo da consulta é obrigatório")
    @Size(max = 100, message = "O tipo da consulta não pode ter mais que 100 caracteres")
    private String tipoConsulta;

    @NotNull(message = "O custo é obrigatório")
    private Float custo;

    @NotNull(message = "O status do sinistro é obrigatório")
    @Size(max = 20, message = "O status do sinistro não pode ter mais que 20 caracteres")
    private String statusSinistro;

    @NotNull(message = "O id do paciente é obrigatório")
    private Long paciente;

    @NotNull(message = "O id do dentista é obrigatório")
    private Long dentista;

}

