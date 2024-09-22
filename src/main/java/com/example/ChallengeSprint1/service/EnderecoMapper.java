package com.example.ChallengeSprint1.service;


import com.example.ChallengeSprint1.dto.EnderecoDTO;
import com.example.ChallengeSprint1.model.Endereco;
import org.springframework.stereotype.Service;

@Service
public class EnderecoMapper {

    // Converte EnderecoDTO para Endereco (entidade)
    public Endereco dtoToEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        enderecoDTO.setCep(endereco.getCep());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        return endereco;
    }

    // Converte Endereco (entidade) para EnderecoDTO
    public EnderecoDTO entityToDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getIdEndereco());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setComplemento(endereco.getComplemento());
        return enderecoDTO;
    }
}
