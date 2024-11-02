package com.example.ChallengeSprint1.service.mapper;

import com.example.ChallengeSprint1.dto.BairroDTO;
import com.example.ChallengeSprint1.model.Bairro;
import com.example.ChallengeSprint1.model.Cidade;
import com.example.ChallengeSprint1.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BairroMapper {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Bairro dtoToEntity(BairroDTO bairroDTO) {
        Bairro bairro = new Bairro();
        bairro.setNome(bairroDTO.getNome());
        Cidade cidade = cidadeRepository.findById(bairroDTO.getCidade())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrado"));
        bairro.setCidade(cidade);
        return bairro;
    }

    public BairroDTO entityToDTO(Bairro bairro) {
        BairroDTO bairroDTO = new BairroDTO();
        bairroDTO.setId(bairro.getCodBairro());
        bairroDTO.setNome(bairro.getNome());
        bairroDTO.setCidade(bairro.getCidade().getCodCidade());
        return bairroDTO;
    }
}
