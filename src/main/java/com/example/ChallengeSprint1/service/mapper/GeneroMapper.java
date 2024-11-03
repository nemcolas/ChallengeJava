package com.example.ChallengeSprint1.service.mapper;


import com.example.ChallengeSprint1.dto.GeneroDTO;
import com.example.ChallengeSprint1.model.Genero;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    public Genero dtoToEntity(GeneroDTO generoDTO) {
        Genero genero = new Genero();
        genero.setDescricao(generoDTO.getDescricao());
        return genero;
    }

    public GeneroDTO entityToDTO(Genero genero) {
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setDescricao(genero.getDescricao());
        return generoDTO;
    }
}
