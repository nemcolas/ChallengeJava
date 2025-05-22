package com.example.ChallengeSprint1.service.mapper;

import com.example.ChallengeSprint1.dto.SinistroDTO;
import com.example.ChallengeSprint1.model.Consulta;
import com.example.ChallengeSprint1.model.Sinistro;
import com.example.ChallengeSprint1.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class SinistroMapper {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Sinistro dtoToEntity(SinistroDTO sinistroDTO) {
        if (sinistroDTO == null) {
            return null;
        }
        
        Sinistro sinistro = new Sinistro();
        sinistro.setMotivoSinistro(sinistroDTO.getMotivoSinistro());
        
        try {
            sinistro.setDataAbertura(sinistroDTO.getDataAbertura());
        } catch (Exception e) {
            throw new RuntimeException("Formato de data inválido: " + sinistroDTO.getDataAbertura());
        }
        
        sinistro.setStatusSinistro(sinistroDTO.getStatusSinistro());
        
        if (sinistroDTO.getConsulta() != null) {
            Consulta consulta = consultaRepository.findById(sinistroDTO.getConsulta())
                    .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + sinistroDTO.getConsulta()));
            sinistro.setConsulta(consulta);
        } else {
            throw new RuntimeException("ID da consulta não pode ser nulo");
        }
        
        return sinistro;
    }

    public SinistroDTO entityToDTO(Sinistro sinistro) {
        if (sinistro == null) {
            return null;
        }
        
        SinistroDTO sinistroDTO = new SinistroDTO();
        sinistroDTO.setMotivoSinistro(sinistro.getMotivoSinistro());
        sinistroDTO.setDataAbertura(sinistro.getDataAbertura());
        sinistroDTO.setStatusSinistro(sinistro.getStatusSinistro());
        
        if (sinistro.getConsulta() != null) {
            sinistroDTO.setConsulta(sinistro.getConsulta().getIdConsulta());
        }
        
        return sinistroDTO;
    }
}
