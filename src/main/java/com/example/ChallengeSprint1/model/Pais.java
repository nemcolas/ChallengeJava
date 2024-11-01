package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CH_PAIS")
@Data
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_PAIS")
    private Long codPais;

    @Column(name = "NOME", nullable = false, length = 30)
    private String nome;

}
