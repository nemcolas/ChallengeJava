package com.example.ChallengeSprint1.model;



import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SINISTRO")
public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinistro")
    private Long idSinistro;

    @Column(name = "motivo_sinistro", nullable = false, length = 255)
    private String motivoSinistro;


    @Column(name = "data_abertura", nullable = false)
    private String dataAbertura;

    @Column(name = "status_sinistro", nullable = false, length = 255)
    private String statusSinistro;

    @ManyToOne
    @JoinColumn(name = "consulta_id_consulta", nullable = false)
    private Consulta consulta;


    public Long getIdSinistro() {
        return idSinistro;
    }

    public void setIdSinistro(Long idSinistro) {
        this.idSinistro = idSinistro;
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

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
