package com.example.ChallengeSprint1.dto;

import lombok.Data;

@Data
public class ConsultaDTO {

    private Long id;
    private String dataConsulta;
    private String tipoConsulta;
    private Float custo;
    private String statusSinistro;
    private Long paciente;
    private Long dentista;

}

