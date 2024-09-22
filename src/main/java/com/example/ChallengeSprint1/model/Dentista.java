package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dentista")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dentista")
    private Long idDentista;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cro", nullable = false, length = 15)
    private String cro;

    @Column(name = "especialidade", nullable = false, length = 50)
    private String especialidade;

    @ManyToOne
    @JoinColumn(name = "endereco_id_endereco", nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "genero_id_genero", nullable = false)
    private Genero genero;

    // Getters e Setters
    // ...
}
