package com.example.ChallengeSprint1.service;

import com.example.ChallengeSprint1.dto.PacienteDTO;
import com.example.ChallengeSprint1.model.Paciente;
import org.springframework.stereotype.Service;

@Service
public class PacienteMapper {

    // Converte Paciente (entidade) para PacienteDTO
    public PacienteDTO entityToDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getIdPaciente());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setCpf(paciente.getCpf());
        pacienteDTO.setDataNascimento(paciente.getDataNascimento());
        return pacienteDTO;
    }

    // Converte PacienteDTO para Paciente (entidade)
    public Paciente dtoToEntity(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteDTO.getNome());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        return paciente;
    }
}