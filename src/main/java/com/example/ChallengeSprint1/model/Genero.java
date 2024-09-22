package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "genero")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Long idGenero;

    @Column(name = "descricao", nullable = false, length = 20)
    private String descricao;

    // Getters e Setters
    // ...
}
