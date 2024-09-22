package com.example.ChallengeSprint1.dto;


public class SinistroDTO {

    private Long id;
    private String motivoSinistro;
    private String dataAbertura;
    private String statusSinistro;
    private String consulta;

    // Construtores, Getters e Setters
    public SinistroDTO() {}

    public SinistroDTO(Long id, String motivoSinistro, String dataAbertura, String statusSinistro, String consulta) {
        this.id = id;
        this.motivoSinistro = motivoSinistro;
        this.dataAbertura = dataAbertura;
        this.statusSinistro = statusSinistro;
        this.consulta = consulta;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivoSinistro() {
        return motivoSinistro;
    }

    public void setMotivoSinistro(String motivoSinistro) {
        this.motivoSinistro = motivoSinistro;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getStatusSinistro() {
        return statusSinistro;
    }

    public void setStatusSinistro(String statusSinistro) {
        this.statusSinistro = statusSinistro;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }
}
