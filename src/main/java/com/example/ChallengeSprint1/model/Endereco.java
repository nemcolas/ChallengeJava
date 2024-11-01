package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CH_ENDERECO")
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_ENDERECO")
    private Long codEndereco;

    @Column(name = "NUMERO", nullable = false, length = 6)
    private Long numero;

    @Column(name = "CEP", nullable = false, length = 8)
    private Long cep;

    @Column(name = "REFERENCIA", length = 30)
    private String referencia;

    @ManyToOne
    @JoinColumn(name = "COD_BAIRRO", nullable = false)
    private Bairro bairro;

    @Column(name = "LOGRADOURO", nullable = false, length = 30)
    private String logradouro;

}

