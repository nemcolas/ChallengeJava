package com.example.ChallengeSprint1.dto;


public class ConsultaDTO {

    private Long id;
    private String dataConsulta;
    private String tipoConsulta;
    private Float custo;
    private String statusSinistro;
    private String paciente;
    private String dentista;

    // Construtores, Getters e Setters
    public ConsultaDTO() {}

    public ConsultaDTO(Long id, String dataConsulta, String tipoConsulta, Float custo, String statusSinistro, String paciente, String dentista) {
        this.id = id;
        this.dataConsulta = dataConsulta;
        this.tipoConsulta = tipoConsulta;
        this.custo = custo;
        this.statusSinistro = statusSinistro;
        this.paciente = paciente;
        this.dentista = dentista;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public Float getCusto() {
        return custo;
    }

    public void setCusto(Float custo) {
        this.custo = custo;
    }

    public String getStatusSinistro() {
        return statusSinistro;
    }

    public void setStatusSinistro(String statusSinistro) {
        this.statusSinistro = statusSinistro;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getDentista() {
        return dentista;
    }

    public void setDentista(String dentista) {
        this.dentista = dentista;
    }
}

