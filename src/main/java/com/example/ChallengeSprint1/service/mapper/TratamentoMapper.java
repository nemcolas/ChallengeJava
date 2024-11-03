package com.example.ChallengeSprint1.service.mapper;

import com.example.ChallengeSprint1.dto.TratamentoDTO;
import com.example.ChallengeSprint1.model.Consulta;
import com.example.ChallengeSprint1.model.Tratamento;
import com.example.ChallengeSprint1.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TratamentoMapper {

    @Autowired
    private ConsultaRepository consultaRepository;

    public TratamentoDTO entityToDTO(Tratamento tratamento) {
        TratamentoDTO tratamentoDTO = new TratamentoDTO();
        tratamentoDTO.setTipoTratamento(tratamento.getTipoTratamento());
        tratamentoDTO.setDescricao(tratamento.getDescricao());
        tratamentoDTO.setCusto(tratamento.getCusto());
        tratamentoDTO.setDataInicio(tratamento.getDataInicio());
        tratamentoDTO.setDataTermino(tratamento.getDataTermino());
        tratamentoDTO.setConsulta(tratamento.getConsulta().getIdConsulta());
        return tratamentoDTO;
    }

    public Tratamento dtoToEntity(TratamentoDTO tratamentoDTO) {
        Tratamento tratamento = new Tratamento();
        tratamento.setTipoTratamento(tratamentoDTO.getTipoTratamento());
        tratamento.setDescricao(tratamentoDTO.getDescricao());
        tratamento.setCusto(tratamentoDTO.getCusto());
        tratamento.setDataInicio(tratamentoDTO.getDataInicio());
        tratamento.setDataTermino(tratamentoDTO.getDataTermino());
        Consulta consulta = consultaRepository.findById(tratamentoDTO.getConsulta())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        tratamento.setConsulta(consulta);
        return tratamento;
    }
}