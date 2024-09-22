package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long idConsulta;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_consulta", nullable = false)
    private Date dataConsulta;

    @Column(name = "tipo_consulta", nullable = false, length = 100)
    private String tipoConsulta;

    @Column(name = "custo", nullable = false)
    private Float custo;

    @Column(name = "status_sinistro", nullable = false, length = 20)
    private String statusSinistro;

    @ManyToOne
    @JoinColumn(name = "paciente_id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "dentista_id_dentista", nullable = false)
    private Dentista dentista;

    // Getters e Setters
    // ...
}

