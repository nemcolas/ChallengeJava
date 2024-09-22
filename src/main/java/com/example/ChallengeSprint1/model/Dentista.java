package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dentista")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dentista")
    private Long idDentista;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cro", nullable = false, length = 15)
    private String cro;

    @Column(name = "especialidade", nullable = false, length = 50)
    private String especialidade;

    @ManyToOne
    @JoinColumn(name = "endereco_id_endereco", nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "genero_id_genero", nullable = false)
    private Genero genero;


    public Long getIdDentista() {
        return idDentista;
    }

    public void setIdDentista(Long idDentista) {
        this.idDentista = idDentista;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
