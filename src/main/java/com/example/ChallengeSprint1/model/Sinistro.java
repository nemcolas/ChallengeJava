package com.example.ChallengeSprint1.model;



import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sinistro")
public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinistro")
    private Long idSinistro;

    @Column(name = "motivo_sinistro", nullable = false, length = 255)
    private String motivoSinistro;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_abertura", nullable = false)
    private Date dataAbertura;

    @Column(name = "status_sinistro", nullable = false, length = 255)
    private String statusSinistro;

    @ManyToOne
    @JoinColumn(name = "consulta_id_consulta", nullable = false)
    private Consulta consulta;

    // Getters e Setters
    // ...
}
