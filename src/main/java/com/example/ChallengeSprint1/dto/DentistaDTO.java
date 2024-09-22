package com.example.ChallengeSprint1.dto;


public class DentistaDTO {

    private Long id;
    private String nome;
    private String cro;
    private String especialidade;
    private String genero;
    private String endereco;

    // Construtores, Getters e Setters
    public DentistaDTO() {}

    public DentistaDTO(Long id, String nome, String cro, String especialidade, String genero, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cro = cro;
        this.especialidade = especialidade;
        this.genero = genero;
        this.endereco = endereco;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}

