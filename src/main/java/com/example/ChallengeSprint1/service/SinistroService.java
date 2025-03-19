package com.example.ChallengeSprint1.service;

import com.example.ChallengeSprint1.dto.SinistroDTO;
import com.example.ChallengeSprint1.model.Sinistro;
import com.example.ChallengeSprint1.repository.SinistroRepository;
import com.example.ChallengeSprint1.service.mapper.SinistroMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SinistroService {

    private final SinistroRepository sinistroRepository;

    private final SinistroMapper sinistroMapper;

    public SinistroService(SinistroRepository sinistroRepository, SinistroMapper sinistroMapper) {
        this.sinistroRepository = sinistroRepository;
        this.sinistroMapper = sinistroMapper;
    }

    public void cadastrarSinistro(SinistroDTO sinistroDTO) {
        Sinistro sinistroConvertido = sinistroMapper.dtoToEntity(sinistroDTO);
        sinistroRepository.save(sinistroConvertido);
    }

    public List<Sinistro> listarSinistros() {
        return sinistroRepository.findAll();
    }

    public void deletarSinistro(Long id) {
        Optional<Sinistro> optionalSinistro = sinistroRepository.findById(id);
        if(optionalSinistro.isPresent()){
            sinistroRepository.delete(optionalSinistro.get());
        } else {
            throw new RuntimeException("Sinistro não encontrado");
        }
    }

    public void atualizarSinistro(Long id, SinistroDTO sinistroDTO) {
        Optional<Sinistro> optionalSinistro = sinistroRepository.findById(id);
        if(optionalSinistro.isPresent()){
            Sinistro sinistroAtualizado = sinistroMapper.dtoToEntity(sinistroDTO);
            sinistroAtualizado.setIdSinistro(id);
            sinistroRepository.save(sinistroAtualizado);
        } else {
            throw new RuntimeException("Sinistro não encontrado");
        }
    }
}
