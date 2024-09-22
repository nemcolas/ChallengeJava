package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "genero_id_genero", nullable = false)
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "endereco_id_endereco", nullable = false)
    private Endereco endereco;

}
