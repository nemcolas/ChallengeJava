package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tratamento")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamento")
    private Long idTratamento;

    @Column(name = "tipo_tratamento", nullable = false, length = 50)
    private String tipoTratamento;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "custo", nullable = false)
    private Float custo;


    @Column(name = "data_inicio", nullable = false)
    private String dataInicio;


    @Column(name = "data_termino", nullable = false)
    private String dataTermino;

    @ManyToOne
    @JoinColumn(name = "consulta_id_consulta", nullable = false)
    private Consulta consulta;

    public Long getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(Long idTratamento) {
        this.idTratamento = idTratamento;
    }

    public String getTipoTratamento() {
        return tipoTratamento;
    }

    public void setTipoTratamento(String tipoTratamento) {
        this.tipoTratamento = tipoTratamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getCusto() {
        return custo;
    }

    public void setCusto(Float custo) {
        this.custo = custo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
