package com.example.ChallengeSprint1.dto;


public class TratamentoDTO {

    private Long id;
    private String tipoTratamento;
    private String descricao;
    private Float custo;
    private String dataInicio;
    private String dataTermino;
    private String consulta;

    // Construtores, Getters e Setters
    public TratamentoDTO() {}

    public TratamentoDTO(Long id, String tipoTratamento, String descricao, Float custo, String dataInicio, String dataTermino, String consulta) {
        this.id = id;
        this.tipoTratamento = tipoTratamento;
        this.descricao = descricao;
        this.custo = custo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.consulta = consulta;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }
}
