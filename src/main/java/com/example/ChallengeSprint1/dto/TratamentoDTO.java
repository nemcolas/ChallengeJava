package com.example.ChallengeSprint1.dto;

import lombok.Data;

@Data
public class TratamentoDTO {

    private Long id;
    private String tipoTratamento;
    private String descricao;
    private Float custo;
    private String dataInicio;
    private String dataTermino;
    private Long consulta;

}
