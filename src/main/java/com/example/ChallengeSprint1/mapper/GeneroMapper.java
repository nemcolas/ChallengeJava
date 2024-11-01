package com.example.ChallengeSprint1.mapper;


import com.example.ChallengeSprint1.dto.GeneroDTO;
import com.example.ChallengeSprint1.model.Genero;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    // Converte GeneroDTO para Genero (entidade)
    public Genero dtoToEntity(GeneroDTO generoDTO) {
        Genero genero = new Genero();
        genero.setDescricao(generoDTO.getDescricao());
        return genero;
    }

    // Converte Genero (entidade) para GeneroDTO
    public GeneroDTO entityToDTO(Genero genero) {
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(genero.getIdGenero());
        generoDTO.setDescricao(genero.getDescricao());
        return generoDTO;
    }
}
