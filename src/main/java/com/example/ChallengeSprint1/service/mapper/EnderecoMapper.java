package com.example.ChallengeSprint1.service.mapper;


import com.example.ChallengeSprint1.dto.EnderecoDTO;
import com.example.ChallengeSprint1.model.Bairro;
import com.example.ChallengeSprint1.model.Endereco;
import com.example.ChallengeSprint1.repository.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    @Autowired
    private BairroRepository bairroRepository;

    public Endereco dtoToEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setCep(enderecoDTO.getCep());
        Bairro bairro = bairroRepository.findById(enderecoDTO.getBairro())
                .orElseThrow(() -> new RuntimeException("Bairro não encontrado"));
        endereco.setBairro(bairro);
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setReferencia(enderecoDTO.getReferencia());
        return endereco;
    }

    public EnderecoDTO entityToDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setBairro(endereco.getBairro().getCodBairro());
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setReferencia(endereco.getReferencia());
        return enderecoDTO;
    }
}
