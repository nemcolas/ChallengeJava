package com.example.ChallengeSprint1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SinistroDTO {

    @NotNull(message = "O motivo é obrigatório")
    @Size(max = 255, message = "O motivo não pode ter mais que 255 caracteres")
    private String motivoSinistro;

    @NotNull(message = "A data de abertura é obrigatória")
    @Size(max = 20, message = "A data de abertura não pode ter mais que 20 caracteres")
    private String dataAbertura;

    @NotNull(message = "O status é obrigatório")
    @Size(max = 20, message = "O status não pode ter mais que 20 caracteres")
    private String statusSinistro;

    @NotNull(message = "O id da consulta é obrigatório")
    private Long consulta;

}
