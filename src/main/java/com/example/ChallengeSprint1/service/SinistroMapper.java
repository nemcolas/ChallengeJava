package com.example.ChallengeSprint1.service;



import com.example.ChallengeSprint1.dto.SinistroDTO;
import com.example.ChallengeSprint1.model.Consulta;
import com.example.ChallengeSprint1.model.Paciente;
import com.example.ChallengeSprint1.model.Sinistro;
import com.example.ChallengeSprint1.repository.ConsultaRepository;
import com.example.ChallengeSprint1.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinistroMapper {

    @Autowired
    private ConsultaRepository consultaRepository;

    // Converte SinistroDTO para Sinistro (entidade)
    public Sinistro dtoToEntity(SinistroDTO sinistroDTO) {
        Sinistro sinistro = new Sinistro();
        sinistro.setMotivoSinistro(sinistroDTO.getMotivoSinistro());
        sinistro.setDataAbertura(sinistroDTO.getDataAbertura());
        sinistro.setStatusSinistro(sinistroDTO.getStatusSinistro());
        Consulta consulta = consultaRepository.findById(sinistroDTO.getConsulta())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        sinistro.setConsulta(consulta);
        return sinistro;
    }

    // Converte Sinistro (entidade) para SinistroDTO
    public SinistroDTO entityToDTO(Sinistro sinistro) {
        SinistroDTO sinistroDTO = new SinistroDTO();
        sinistroDTO.setId(sinistro.getIdSinistro());
        sinistroDTO.setMotivoSinistro(sinistro.getMotivoSinistro());
        sinistroDTO.setDataAbertura(String.valueOf(sinistro.getDataAbertura()));
        sinistroDTO.setStatusSinistro(sinistro.getStatusSinistro());
        sinistroDTO.setConsulta(sinistro.getConsulta().getIdConsulta());
        return sinistroDTO;
    }
}

