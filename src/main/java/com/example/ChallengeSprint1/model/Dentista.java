package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CH_DENTISTA")
@Data
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DENTISTA")
    private Long idDentista;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "CRO", nullable = false, length = 15)
    private String cro;

    @Column(name = "ESPECIALIDADE", nullable = false, length = 50)
    private String especialidade;

    @ManyToOne
    @JoinColumn(name = "ENDERECO_ID_ENDERECO", nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "GENERO_ID_GENERO", nullable = false)
    private Genero genero;

}
