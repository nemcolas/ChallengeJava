package com.example.ChallengeSprint1.service.mapper;

import com.example.ChallengeSprint1.dto.CidadeDTO;
import com.example.ChallengeSprint1.model.Cidade;
import com.example.ChallengeSprint1.model.Estado;
import com.example.ChallengeSprint1.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeMapper {

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade dtoToEntity(CidadeDTO cidadeDTO) {
        Cidade cidade = new Cidade();
        cidade.setNome(cidadeDTO.getNome());
        Estado estado = estadoRepository.findById(cidadeDTO.getEstado())
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        cidade.setEstado(estado);
        return cidade;
    }

    public CidadeDTO entityToDTO(Cidade cidade) {
        CidadeDTO cidadeDTO = new CidadeDTO();
        cidadeDTO.setNome(cidade.getNome());
        cidadeDTO.setEstado(cidade.getEstado().getCodEstado());
        return cidadeDTO;
    }
}
