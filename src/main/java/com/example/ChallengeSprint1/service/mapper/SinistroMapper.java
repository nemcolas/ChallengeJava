package com.example.ChallengeSprint1.service.mapper;



import com.example.ChallengeSprint1.dto.SinistroDTO;
import com.example.ChallengeSprint1.model.Consulta;
import com.example.ChallengeSprint1.model.Sinistro;
import com.example.ChallengeSprint1.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SinistroMapper {

    @Autowired
    private ConsultaRepository consultaRepository;

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

    public SinistroDTO entityToDTO(Sinistro sinistro) {
        SinistroDTO sinistroDTO = new SinistroDTO();
        sinistroDTO.setMotivoSinistro(sinistro.getMotivoSinistro());
        sinistroDTO.setDataAbertura(String.valueOf(sinistro.getDataAbertura()));
        sinistroDTO.setStatusSinistro(sinistro.getStatusSinistro());
        sinistroDTO.setConsulta(sinistro.getConsulta().getIdConsulta());
        return sinistroDTO;
    }
}

