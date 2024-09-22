package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long idConsulta;


    @Column(name = "data_consulta", nullable = false)
    private String dataConsulta;

    @Column(name = "tipo_consulta", nullable = false, length = 100)
    private String tipoConsulta;

    @Column(name = "custo", nullable = false)
    private Float custo;

    @Column(name = "status_sinistro", nullable = false, length = 20)
    private String statusSinistro;

    @ManyToOne
    @JoinColumn(name = "paciente_id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "dentista_id_dentista", nullable = false)
    private Dentista dentista;

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }
}

