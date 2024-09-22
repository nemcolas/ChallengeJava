package com.example.ChallengeSprint1.service;

import com.example.ChallengeSprint1.dto.ConsultaDTO;
import com.example.ChallengeSprint1.model.Consulta;
import org.springframework.stereotype.Service;

@Service
public class ConsultaMapper {

    // Converte Consulta (entidade) para ConsultaDTO
    public ConsultaDTO entityToDTO(Consulta consulta) {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setId(consulta.getIdConsulta());
        consultaDTO.setTipoConsulta(consulta.getTipoConsulta());
        consultaDTO.setCusto(consulta.getCusto());
        consultaDTO.setStatusSinistro(consulta.getStatusSinistro());
        consultaDTO.setDataConsulta(consulta.getDataConsulta());
        return consultaDTO;
    }

    // Converte ConsultaDTO para Consulta (entidade)
    public Consulta dtoToEntity(ConsultaDTO consultaDTO) {
        Consulta consulta = new Consulta();
        consulta.setTipoConsulta(consultaDTO.getTipoConsulta());
        consulta.setCusto(consultaDTO.getCusto());
        consulta.setStatusSinistro(consultaDTO.getStatusSinistro());
        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        return consulta;
    }
}