package com.example.ChallengeSprint1.dto;

import lombok.Data;

@Data
public class SinistroDTO {

    private Long id;
    private String motivoSinistro;
    private String dataAbertura;
    private String statusSinistro;
    private Long consulta;

}
