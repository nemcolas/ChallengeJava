package com.example.ChallengeSprint1.service;

import com.example.ChallengeSprint1.dto.ConsultaDTO;
import com.example.ChallengeSprint1.model.Consulta;
import com.example.ChallengeSprint1.repository.ConsultaRepository;
import com.example.ChallengeSprint1.service.mapper.ConsultaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    private final ConsultaMapper consultaMapper;

    public ConsultaService(ConsultaRepository consultaRepository, ConsultaMapper consultaMapper) {
        this.consultaRepository = consultaRepository;
        this.consultaMapper = consultaMapper;
    }

    public void cadastrarConsulta(ConsultaDTO consultaDTO) {
        Consulta consultaConvertido = consultaMapper.dtoToEntity(consultaDTO);
        consultaRepository.save(consultaConvertido);
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public void deletarConsulta(Long id) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);
        if(optionalConsulta.isPresent()){
            consultaRepository.delete(optionalConsulta.get());
        } else {
            throw new RuntimeException("Consulta não encontrado");
        }
    }

    public void atualizarConsulta(Long id, ConsultaDTO consultaDTO) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);
        if(optionalConsulta.isPresent()){
            Consulta consultaAtualizado = consultaMapper.dtoToEntity(consultaDTO);
            consultaAtualizado.setIdConsulta(id);
            consultaRepository.save(consultaAtualizado);
        } else {
            throw new RuntimeException("Consulta não encontrado");
        }
    }
}
