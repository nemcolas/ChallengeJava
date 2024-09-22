package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tratamento")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamento")
    private Long idTratamento;

    @Column(name = "tipo_tratamento", nullable = false, length = 50)
    private String tipoTratamento;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "custo", nullable = false)
    private Float custo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicio", nullable = false)
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_termino", nullable = false)
    private Date dataTermino;

    @ManyToOne
    @JoinColumn(name = "consulta_id_consulta", nullable = false)
    private Consulta consulta;

    // Getters e Setters
    // ...
}
