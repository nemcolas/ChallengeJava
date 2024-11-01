package com.example.ChallengeSprint1.service.mapper;

import com.example.ChallengeSprint1.dto.EstadoDTO;
import com.example.ChallengeSprint1.model.Estado;
import com.example.ChallengeSprint1.model.Pais;
import com.example.ChallengeSprint1.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoMapper {

    @Autowired
    private PaisRepository paisRepository;

    // Converte EstadoDTO para Estado (entidade)
    public Estado dtoToEntity(EstadoDTO estadoDTO) {
        Estado estado = new Estado();
        estado.setNome(estadoDTO.getNome());
        Pais pais = paisRepository.findById(estadoDTO.getPais())
                .orElseThrow(() -> new RuntimeException("Pais não encontrado"));
        estado.setPais(pais);
        return estado;
    }

    // Converte Estado (entidade) para EstadoDTO
    public EstadoDTO entityToDTO(Estado estado) {
        EstadoDTO estadoDTO = new EstadoDTO();
        estadoDTO.setId(estado.getCodEstado());
        estadoDTO.setNome(estado.getNome());
        estadoDTO.setPais(estado.getPais().getCodPais());
        return estadoDTO;
    }
}
