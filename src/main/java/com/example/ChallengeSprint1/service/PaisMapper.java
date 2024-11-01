package com.example.ChallengeSprint1.service;

import com.example.ChallengeSprint1.dto.PaisDTO;
import com.example.ChallengeSprint1.model.Pais;
import org.springframework.stereotype.Service;

@Service
public class PaisMapper {

    // Converte PaisDTO para Pais (entidade)
    public Pais dtoToEntity(PaisDTO paisDTO) {
        Pais pais = new Pais();
        pais.setNome(paisDTO.getNome());
        return pais;
    }

    // Converte Pais (entidade) para PaisDTO
    public PaisDTO entityToDTO(Pais pais) {
        PaisDTO paisDTO = new PaisDTO();
        paisDTO.setId(pais.getCodPais());
        paisDTO.setNome(pais.getNome());
        return paisDTO;
    }
}
