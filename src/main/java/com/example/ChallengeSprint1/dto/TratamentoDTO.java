package com.example.ChallengeSprint1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TratamentoDTO {

    @NotNull(message = "O tipo de tratamento é obrigatório")
    @Size(max = 50, message = "O tipo de tratamento não pode ter mais que 50 caracteres")
    private String tipoTratamento;

    @NotNull(message = "A descrição é obrigatória")
    @Size(max = 255, message = "A descrição não pode ter mais que 255 caracteres")
    private String descricao;

    @NotNull(message = "O custo é obrigatório")
    private Float custo;

    @NotNull(message = "A data de início é obrigatória")
    @Size(max = 20, message = "A data de início não pode ter mais que 20 caracteres")
    private String dataInicio;

    @NotNull(message = "A data de término é obrigatória")
    @Size(max = 20, message = "A data de término não pode ter mais que 20 caracteres")
    private String dataTermino;

    @NotNull(message = "O id da consulta é obrigatório")
    private Long consulta;

}
