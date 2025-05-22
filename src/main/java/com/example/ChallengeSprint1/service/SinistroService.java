package com.example.ChallengeSprint1.service;

import com.example.ChallengeSprint1.dto.SinistroDTO;
import com.example.ChallengeSprint1.model.Consulta;
import com.example.ChallengeSprint1.model.Sinistro;
import com.example.ChallengeSprint1.repository.ConsultaRepository;
import com.example.ChallengeSprint1.repository.SinistroRepository;
import com.example.ChallengeSprint1.service.mapper.SinistroMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SinistroService {

    private final SinistroRepository sinistroRepository;
    private final SinistroMapper sinistroMapper;
    private final ConsultaRepository consultaRepository;

    public SinistroService(SinistroRepository sinistroRepository, SinistroMapper sinistroMapper,
                          ConsultaRepository consultaRepository) {
        this.sinistroRepository = sinistroRepository;
        this.sinistroMapper = sinistroMapper;
        this.consultaRepository = consultaRepository;
    }

    @Transactional
    public void cadastrarSinistro(SinistroDTO sinistroDTO) {
        validarReferencias(sinistroDTO);
        Sinistro sinistroConvertido = sinistroMapper.dtoToEntity(sinistroDTO);
        sinistroRepository.save(sinistroConvertido);
    }

    public List<Sinistro> listarSinistros() {
        return sinistroRepository.findAll();
    }

    @Transactional
    public void deletarSinistro(Long id) {
        Optional<Sinistro> optionalSinistro = sinistroRepository.findById(id);
        if(optionalSinistro.isPresent()){
            try {
                sinistroRepository.delete(optionalSinistro.get());
            } catch (Exception e) {
                throw new RuntimeException("Não foi possível excluir o sinistro: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("Sinistro não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void atualizarSinistro(Long id, SinistroDTO sinistroDTO) {
        Optional<Sinistro> optionalSinistro = sinistroRepository.findById(id);
        if(optionalSinistro.isPresent()){
            validarReferencias(sinistroDTO);
            Sinistro sinistroAtualizado = sinistroMapper.dtoToEntity(sinistroDTO);
            sinistroAtualizado.setIdSinistro(id);
            sinistroRepository.save(sinistroAtualizado);
        } else {
            throw new RuntimeException("Sinistro não encontrado com o ID: " + id);
        }
    }
    
    private void validarReferencias(SinistroDTO sinistroDTO) {
        // Validar consulta
        if (sinistroDTO.getConsulta() == null) {
            throw new RuntimeException("ID da consulta não pode ser nulo");
        }
        Optional<Consulta> consulta = consultaRepository.findById(sinistroDTO.getConsulta());
        if (consulta.isEmpty()) {
            throw new RuntimeException("Consulta não encontrada com o ID: " + sinistroDTO.getConsulta());
        }
    }
}
