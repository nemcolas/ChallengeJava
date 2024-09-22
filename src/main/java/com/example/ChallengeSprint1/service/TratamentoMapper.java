package com.example.ChallengeSprint1.service;

import com.example.ChallengeSprint1.dto.TratamentoDTO;
import com.example.ChallengeSprint1.model.Tratamento;
import org.springframework.stereotype.Service;

@Service
public class TratamentoMapper {

    // Converte Tratamento (entidade) para TratamentoDTO
    public TratamentoDTO entityToDTO(Tratamento tratamento) {
        TratamentoDTO tratamentoDTO = new TratamentoDTO();
        tratamentoDTO.setId(tratamento.getIdTratamento());
        tratamentoDTO.setTipoTratamento(tratamento.getTipoTratamento());
        tratamentoDTO.setDescricao(tratamento.getDescricao());
        tratamentoDTO.setCusto(tratamento.getCusto());
        tratamentoDTO.setDataInicio(tratamento.getDataInicio());
        tratamentoDTO.setDataTermino(tratamento.getDataTermino());
        return tratamentoDTO;
    }

    // Converte TratamentoDTO para Tratamento (entidade)
    public Tratamento dtoToEntity(TratamentoDTO tratamentoDTO) {
        Tratamento tratamento = new Tratamento();
        tratamento.setTipoTratamento(tratamentoDTO.getTipoTratamento());
        tratamento.setDescricao(tratamentoDTO.getDescricao());
        tratamento.setCusto(tratamentoDTO.getCusto());
        tratamento.setDataInicio(tratamentoDTO.getDataInicio());
        tratamento.setDataTermino(tratamentoDTO.getDataTermino());
        return tratamento;
    }
}