package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "CH_PACIENTE")
@Data
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PACIENTE")
    private Long idPaciente;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "DATA_NASCIMENTO", nullable = false, length = 20)
    private String dataNascimento;

    @Column(name = "CPF", nullable = false, length = 11)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "GENERO_ID_GENERO", nullable = false)
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "ENDERECO_ID_ENDERECO", nullable = false)
    private Endereco endereco;

}
