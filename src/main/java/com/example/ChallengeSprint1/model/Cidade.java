package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CH_CIDADE")
@Data
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CIDADE")
    private Long codCidade;

    @Column(name = "NOME", nullable = false, length = 30)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "COD_ESTADO", nullable = false)
    private Estado estado;

}
