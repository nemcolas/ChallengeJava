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


    @Column(name = "data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "genero_id_genero", nullable = false)
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "endereco_id_endereco", nullable = false)
    private Endereco endereco;

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
