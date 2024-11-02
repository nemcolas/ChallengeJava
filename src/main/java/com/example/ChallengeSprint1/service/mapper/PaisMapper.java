package com.example.ChallengeSprint1.service.mapper;

import com.example.ChallengeSprint1.dto.PaisDTO;
import com.example.ChallengeSprint1.model.Pais;
import org.springframework.stereotype.Component;

@Component
public class PaisMapper {

    public Pais dtoToEntity(PaisDTO paisDTO) {
        Pais pais = new Pais();
        pais.setNome(paisDTO.getNome());
        return pais;
    }

    public PaisDTO entityToDTO(Pais pais) {
        PaisDTO paisDTO = new PaisDTO();
        paisDTO.setId(pais.getCodPais());
        paisDTO.setNome(pais.getNome());
        return paisDTO;
    }
}
