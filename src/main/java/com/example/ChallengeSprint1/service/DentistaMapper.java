package com.example.ChallengeSprint1.service;

import com.example.ChallengeSprint1.dto.DentistaDTO;
import com.example.ChallengeSprint1.model.Dentista;
import org.springframework.stereotype.Service;

@Service
public class DentistaMapper {

    // Converte DentistaDTO para Dentista (entidade)
    public Dentista dtoToEntity(DentistaDTO dentistaDTO) {
        Dentista dentista = new Dentista();
        dentista.setNome(dentistaDTO.getNome());
        dentista.setCro(dentistaDTO.getCro());
        dentista.setEspecialidade(dentistaDTO.getEspecialidade());
        return dentista;
    }

    // Converte Dentista (entidade) para DentistaDTO
    public DentistaDTO entityToDTO(Dentista dentista) {
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setId(dentista.getIdDentista());
        dentistaDTO.setNome(dentista.getNome());
        dentistaDTO.setCro(dentista.getCro());
        dentistaDTO.setEspecialidade(dentista.getEspecialidade());
        return dentistaDTO;
    }
}
