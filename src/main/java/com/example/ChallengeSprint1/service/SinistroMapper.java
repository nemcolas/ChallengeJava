package com.example.ChallengeSprint1.service;



import com.example.ChallengeSprint1.dto.SinistroDTO;
import com.example.ChallengeSprint1.model.Sinistro;
import org.springframework.stereotype.Service;

@Service
public class SinistroMapper {

    // Converte SinistroDTO para Sinistro (entidade)
    public Sinistro dtoToEntity(SinistroDTO sinistroDTO) {
        Sinistro sinistro = new Sinistro();
        sinistro.setMotivoSinistro(sinistroDTO.getMotivoSinistro());
        sinistro.setDataAbertura(sinistroDTO.getDataAbertura());
        sinistro.setStatusSinistro(sinistroDTO.getStatusSinistro());
        return sinistro;
    }

    // Converte Sinistro (entidade) para SinistroDTO
    public SinistroDTO entityToDTO(Sinistro sinistro) {
        SinistroDTO sinistroDTO = new SinistroDTO();
        sinistroDTO.setId(sinistro.getIdSinistro());
        sinistroDTO.setMotivoSinistro(sinistro.getMotivoSinistro());
        sinistroDTO.setDataAbertura(String.valueOf(sinistro.getDataAbertura()));
        sinistroDTO.setStatusSinistro(sinistro.getStatusSinistro());
        return sinistroDTO;
    }
}

