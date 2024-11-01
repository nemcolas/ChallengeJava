package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CH_ESTADO")
@Data
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_ESTADO")
    private Long codEstado;

    @Column(name = "NOME_ESTADO", nullable = false, length = 30)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "COD_PAIS", nullable = false)
    private Pais pais;

}
