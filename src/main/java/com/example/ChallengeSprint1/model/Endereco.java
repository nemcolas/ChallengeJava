package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long idEndereco;

    @Column(name = "cep", nullable = false, length = 15)
    private String cep;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "bairro", nullable = false, length = 100)
    private String bairro;

    @Column(name = "rua", nullable = false, length = 255)
    private String rua;

    @Column(name = "numero", nullable = false, length = 10)
    private String numero;

    @Column(name = "complemento", length = 100)
    private String complemento;

    // Getters e Setters
    // ...
}

