package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CH_SINISTRO")
@Data
public class Sinistro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SINISTRO")
    private Long idSinistro;

    @Column(name = "MOTIVO_SINISTRO", nullable = false, length = 255)
    private String motivoSinistro;

    @Column(name = "DATA_ABERTURA", nullable = false, length = 20)
    private String dataAbertura;

    @Column(name = "STATUS_SINISTRO", nullable = false, length = 20)
    private String statusSinistro;

    @ManyToOne
    @JoinColumn(name = "CONSULTA_ID_CONSULTA", nullable = false)
    private Consulta consulta;

}
