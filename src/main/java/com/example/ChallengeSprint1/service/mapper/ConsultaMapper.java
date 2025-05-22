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

    public ConsultaDTO entityToDTO(Consulta consulta) {
        if (consulta == null) {
            return null;
        }
        
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setTipoConsulta(consulta.getTipoConsulta());
        consultaDTO.setCusto(consulta.getCusto());
        consultaDTO.setStatusSinistro(consulta.getStatusSinistro());
        consultaDTO.setDataConsulta(consulta.getDataConsulta());
        
        if (consulta.getPaciente() != null) {
            consultaDTO.setPaciente(consulta.getPaciente().getIdPaciente());
        }
        
        if (consulta.getDentista() != null) {
            consultaDTO.setDentista(consulta.getDentista().getIdDentista());
        }
        
        return consultaDTO;
    }

    public Consulta dtoToEntity(ConsultaDTO consultaDTO) {
        if (consultaDTO == null) {
            return null;
        }
        
        Consulta consulta = new Consulta();
        consulta.setTipoConsulta(consultaDTO.getTipoConsulta());
        consulta.setCusto(consultaDTO.getCusto());
        consulta.setStatusSinistro(consultaDTO.getStatusSinistro());
        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        
        if (consultaDTO.getPaciente() != null) {
            Paciente paciente = pacienteRepository.findById(consultaDTO.getPaciente())
                    .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + consultaDTO.getPaciente()));
            consulta.setPaciente(paciente);
        }
        
        if (consultaDTO.getDentista() != null) {
            Dentista dentista = dentistaRepository.findById(consultaDTO.getDentista())
                    .orElseThrow(() -> new RuntimeException("Dentista não encontrado com ID: " + consultaDTO.getDentista()));
            consulta.setDentista(dentista);
        }
        
        return consulta;
    }
}
