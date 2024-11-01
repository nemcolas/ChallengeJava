package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.EnderecoDTO;
import com.example.ChallengeSprint1.model.Endereco;
import com.example.ChallengeSprint1.repository.EnderecoRepository;
import com.example.ChallengeSprint1.service.mapper.EnderecoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/endereco", produces = "application/json")
@Tag(name = "api-endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @PostMapping
    @Operation(summary = "Cria um endereco e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereco cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<EntityModel<EnderecoDTO>> criarEndereco(@Valid @RequestBody EnderecoDTO enderecoDTO) {
        Endereco enderecoConvertido = enderecoMapper.dtoToEntity(enderecoDTO);
        Endereco enderecoCriado = enderecoRepository.save(enderecoConvertido);
        EnderecoDTO enderecoResponse = enderecoMapper.entityToDTO(enderecoCriado);
        EntityModel<EnderecoDTO> resource = EntityModel.of(enderecoResponse,
                linkTo(methodOn(EnderecoController.class).getEnderecoById(enderecoResponse.getId())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todos os enderecos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum endereco encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Enderecos retornados com sucesso")
    })
    public ResponseEntity<CollectionModel<EntityModel<EnderecoDTO>>> getAllEnderecos() {
        List<Endereco> listaEnderecos = enderecoRepository.findAll();
        if (listaEnderecos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EntityModel<EnderecoDTO>> enderecosComLinks = listaEnderecos.stream()
                .map(endereco -> {
                    EnderecoDTO enderecoDTO = enderecoMapper.entityToDTO(endereco);
                    return EntityModel.of(enderecoDTO,
                            linkTo(methodOn(EnderecoController.class).getEnderecoById(enderecoDTO.getId())).withSelfRel());
                })
                .collect(Collectors.toList());
        CollectionModel<EntityModel<EnderecoDTO>> collectionModel = CollectionModel.of(enderecosComLinks,
                linkTo(methodOn(EnderecoController.class).getAllEnderecos()).withSelfRel());
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um endereco pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereco não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Endereco encontrado com sucesso")
    })
    public ResponseEntity<EntityModel<EnderecoDTO>> getEnderecoById(@PathVariable Long id) {
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(id);
        if (enderecoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        EnderecoDTO enderecoResponse = enderecoMapper.entityToDTO(enderecoSalvo.get());
        EntityModel<EnderecoDTO> resource = EntityModel.of(enderecoResponse,
                linkTo(methodOn(EnderecoController.class).getEnderecoById(enderecoResponse.getId())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um endereco existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Endereco não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso")
    })
    public ResponseEntity<EntityModel<EnderecoDTO>> updateEndereco(@PathVariable Long id, @Valid @RequestBody EnderecoDTO enderecoDTO) {
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(id);
        if (enderecoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Endereco enderecoAtualizado = enderecoMapper.dtoToEntity(enderecoDTO);
        enderecoAtualizado.setCodEndereco(id);
        Endereco enderecoSalvoAtualizado = enderecoRepository.save(enderecoAtualizado);
        EnderecoDTO enderecoResponse = enderecoMapper.entityToDTO(enderecoSalvoAtualizado);
        EntityModel<EnderecoDTO> resource = EntityModel.of(enderecoResponse,
                linkTo(methodOn(EnderecoController.class).getEnderecoById(enderecoResponse.getId())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um endereco do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Endereco não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Endereco deletado com sucesso")
    })
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(id);
        if (enderecoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        enderecoRepository.delete(enderecoSalvo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}