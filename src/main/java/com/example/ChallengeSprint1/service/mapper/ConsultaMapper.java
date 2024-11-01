package com.example.ChallengeSprint1.service.mapper;

import com.example.ChallengeSprint1.dto.ConsultaDTO;
import com.example.ChallengeSprint1.model.Consulta;
import com.example.ChallengeSprint1.model.Dentista;
import com.example.ChallengeSprint1.model.Paciente;
import com.example.ChallengeSprint1.repository.DentistaRepository;
import com.example.ChallengeSprint1.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaMapper {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    // Converte Consulta (entidade) para ConsultaDTO
    public ConsultaDTO entityToDTO(Consulta consulta) {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setId(consulta.getIdConsulta());
        consultaDTO.setTipoConsulta(consulta.getTipoConsulta());
        consultaDTO.setCusto(consulta.getCusto());
        consultaDTO.setStatusSinistro(consulta.getStatusSinistro());
        consultaDTO.setDataConsulta(consulta.getDataConsulta());
        consultaDTO.setPaciente(consulta.getPaciente().getIdPaciente());
        consultaDTO.setDentista(consulta.getDentista().getIdDentista());
        return consultaDTO;
    }

    // Converte ConsultaDTO para Consulta (entidade)
    public Consulta dtoToEntity(ConsultaDTO consultaDTO) {
        Consulta consulta = new Consulta();
        consulta.setTipoConsulta(consultaDTO.getTipoConsulta());
        consulta.setCusto(consultaDTO.getCusto());
        consulta.setStatusSinistro(consultaDTO.getStatusSinistro());
        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        Paciente paciente = pacienteRepository.findById(consultaDTO.getPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        consulta.setPaciente(paciente);
        Dentista dentista = dentistaRepository.findById(consultaDTO.getDentista())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));
        consulta.setDentista(dentista);
        return consulta;
    }
}