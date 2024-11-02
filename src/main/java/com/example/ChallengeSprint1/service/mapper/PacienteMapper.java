package com.example.ChallengeSprint1.service.mapper;

import com.example.ChallengeSprint1.dto.PacienteDTO;
import com.example.ChallengeSprint1.model.Endereco;
import com.example.ChallengeSprint1.model.Genero;
import com.example.ChallengeSprint1.model.Paciente;
import com.example.ChallengeSprint1.repository.EnderecoRepository;
import com.example.ChallengeSprint1.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public PacienteDTO entityToDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getIdPaciente());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setCpf(paciente.getCpf());
        pacienteDTO.setDataNascimento(paciente.getDataNascimento());
        pacienteDTO.setGenero(paciente.getGenero().getIdGenero());
        pacienteDTO.setEndereco(paciente.getEndereco().getCodEndereco());
        return pacienteDTO;
    }

    public Paciente dtoToEntity(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteDTO.getNome());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        Genero genero = generoRepository.findById(pacienteDTO.getGenero())
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));
        paciente.setGenero(genero);
        Endereco endereco = enderecoRepository.findById(pacienteDTO.getEndereco())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        paciente.setEndereco(endereco);
        return paciente;
    }
}