package com.example.ChallengeSprint1.service;

import com.example.ChallengeSprint1.dto.ConsultaDTO;
import com.example.ChallengeSprint1.model.Consulta;
import com.example.ChallengeSprint1.model.Dentista;
import com.example.ChallengeSprint1.model.Paciente;
import com.example.ChallengeSprint1.repository.ConsultaRepository;
import com.example.ChallengeSprint1.repository.DentistaRepository;
import com.example.ChallengeSprint1.repository.PacienteRepository;
import com.example.ChallengeSprint1.service.mapper.ConsultaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final ConsultaMapper consultaMapper;
    private final PacienteRepository pacienteRepository;
    private final DentistaRepository dentistaRepository;

    public ConsultaService(ConsultaRepository consultaRepository, ConsultaMapper consultaMapper,
                          PacienteRepository pacienteRepository, DentistaRepository dentistaRepository) {
        this.consultaRepository = consultaRepository;
        this.consultaMapper = consultaMapper;
        this.pacienteRepository = pacienteRepository;
        this.dentistaRepository = dentistaRepository;
    }

    @Transactional
    public void cadastrarConsulta(ConsultaDTO consultaDTO) {
        validarReferencias(consultaDTO);
        Consulta consultaConvertido = consultaMapper.dtoToEntity(consultaDTO);
        consultaRepository.save(consultaConvertido);
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    @Transactional
    public void deletarConsulta(Long id) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);
        if(optionalConsulta.isPresent()){
            try {
                consultaRepository.delete(optionalConsulta.get());
            } catch (Exception e) {
                throw new RuntimeException("Não foi possível excluir a consulta. Verifique se há sinistros associados.");
            }
        } else {
            throw new RuntimeException("Consulta não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public void atualizarConsulta(Long id, ConsultaDTO consultaDTO) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);
        if(optionalConsulta.isPresent()){
            validarReferencias(consultaDTO);
            Consulta consultaAtualizado = consultaMapper.dtoToEntity(consultaDTO);
            consultaAtualizado.setIdConsulta(id);
            consultaRepository.save(consultaAtualizado);
        } else {
            throw new RuntimeException("Consulta não encontrada com o ID: " + id);
        }
    }
    
    private void validarReferencias(ConsultaDTO consultaDTO) {
        // Validar paciente
        if (consultaDTO.getPaciente() == null) {
            throw new RuntimeException("ID do paciente não pode ser nulo");
        }
        Optional<Paciente> paciente = pacienteRepository.findById(consultaDTO.getPaciente());
        if (paciente.isEmpty()) {
            throw new RuntimeException("Paciente não encontrado com o ID: " + consultaDTO.getPaciente());
        }
        
        // Validar dentista
        if (consultaDTO.getDentista() == null) {
            throw new RuntimeException("ID do dentista não pode ser nulo");
        }
        Optional<Dentista> dentista = dentistaRepository.findById(consultaDTO.getDentista());
        if (dentista.isEmpty()) {
            throw new RuntimeException("Dentista não encontrado com o ID: " + consultaDTO.getDentista());
        }
    }
}
