package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "CH_CONSULTA")
@Data
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONSULTA")
    private Long idConsulta;

    @Column(name = "DATA_CONSULTA", nullable = false, length = 20)
    private String dataConsulta;

    @Column(name = "TIPO_CONSULTA", nullable = false, length = 100)
    private String tipoConsulta;

    @Column(name = "CUSTO", nullable = false)
    private Float custo;

    @Column(name = "STATUS_SINISTRO", nullable = false, length = 20)
    private String statusSinistro;

    @ManyToOne
    @JoinColumn(name = "PACIENTE_ID_PACIENTE", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "DENTISTA_ID_DENTISTA", nullable = false)
    private Dentista dentista;

}

