package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CH_GENERO")
@Data
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GENERO")
    private Long idGenero;

    @Column(name = "DESCRICAO", nullable = false, length = 20)
    private String descricao;

}
