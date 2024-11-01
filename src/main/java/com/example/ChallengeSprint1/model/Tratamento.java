package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CH_TRATAMENTO")
@Data
public class Tratamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRATAMENTO")
    private Long idTratamento;

    @Column(name = "TIPO_TRATAMENTO", nullable = false, length = 50)
    private String tipoTratamento;

    @Column(name = "DESCRICAO", nullable = false, length = 255)
    private String descricao;

    @Column(name = "CUSTO", nullable = false)
    private Float custo;

    @Column(name = "DATA_INICIO", nullable = false, length = 20)
    private String dataInicio;

    @Column(name = "DATA_TERMINO", nullable = false, length = 20)
    private String dataTermino;

    @ManyToOne
    @JoinColumn(name = "CONSULTA_ID_CONSULTA", nullable = false)
    private Consulta consulta;

}
