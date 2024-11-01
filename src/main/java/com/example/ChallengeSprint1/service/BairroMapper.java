package com.example.ChallengeSprint1.service;

import com.example.ChallengeSprint1.dto.BairroDTO;
import com.example.ChallengeSprint1.model.Bairro;
import com.example.ChallengeSprint1.model.Cidade;
import com.example.ChallengeSprint1.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BairroMapper {

    @Autowired
    private CidadeRepository cidadeRepository;

    // Converte BairroDTO para Bairro (entidade)
    public Bairro dtoToEntity(BairroDTO bairroDTO) {
        Bairro bairro = new Bairro();
        bairro.setNome(bairroDTO.getNome());
        Cidade cidade = cidadeRepository.findById(bairroDTO.getCidade())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrado"));
        bairro.setCidade(cidade);
        return bairro;
    }

    // Converte Bairro (entidade) para BairroDTO
    public BairroDTO entityToDTO(Bairro bairro) {
        BairroDTO bairroDTO = new BairroDTO();
        bairroDTO.setId(bairro.getCodBairro());
        bairroDTO.setNome(bairro.getNome());
        bairroDTO.setCidade(bairro.getCidade().getCodCidade());
        return bairroDTO;
    }
}
