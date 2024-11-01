package com.example.ChallengeSprint1.dto;

import lombok.Data;

@Data
public class EnderecoDTO {

    private Long id;
    private Long cep;
    private Long bairro;
    private String logradouro;
    private Long numero;
    private String REFERENCIA;

}
