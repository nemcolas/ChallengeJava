package com.example.ChallengeSprint1.service.mapper;

import com.example.ChallengeSprint1.dto.DentistaDTO;
import com.example.ChallengeSprint1.model.Dentista;
import com.example.ChallengeSprint1.model.Endereco;
import com.example.ChallengeSprint1.model.Genero;
import com.example.ChallengeSprint1.repository.EnderecoRepository;
import com.example.ChallengeSprint1.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DentistaMapper {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Dentista dtoToEntity(DentistaDTO dentistaDTO) {
        Dentista dentista = new Dentista();
        dentista.setNome(dentistaDTO.getNome());
        dentista.setCro(dentistaDTO.getCro());
        dentista.setEspecialidade(dentistaDTO.getEspecialidade());
        Genero genero = generoRepository.findById(dentistaDTO.getGenero())
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));
        dentista.setGenero(genero);
        Endereco endereco = enderecoRepository.findById(dentistaDTO.getEndereco())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        dentista.setEndereco(endereco);
        return dentista;
    }

    public DentistaDTO entityToDTO(Dentista dentista) {
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setId(dentista.getIdDentista());
        dentistaDTO.setNome(dentista.getNome());
        dentistaDTO.setCro(dentista.getCro());
        dentistaDTO.setEspecialidade(dentista.getEspecialidade());
        dentistaDTO.setGenero(dentista.getGenero().getIdGenero());
        dentistaDTO.setEndereco(dentista.getEndereco().getCodEndereco());
        return dentistaDTO;
    }
}
