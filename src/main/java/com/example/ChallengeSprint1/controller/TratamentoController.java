package com.example.ChallengeSprint1.controller;


import com.example.ChallengeSprint1.dto.TratamentoDTO;
import com.example.ChallengeSprint1.model.Tratamento;
import com.example.ChallengeSprint1.repository.TratamentoRepository;
import com.example.ChallengeSprint1.mapper.TratamentoMapper;
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
@RequestMapping(value = "/tratamentos", produces = {"application/json"})
@Tag(name = "api-tratamentos")
public class TratamentoController {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Autowired
    private TratamentoMapper tratamentoMapper;

    // Cria um novo tratamento
    @Operation(summary = "Cria um tratamento e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tratamento cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<EntityModel<TratamentoDTO>> createTratamento(@Valid @RequestBody TratamentoDTO tratamentoDTO) {
        Tratamento tratamentoConvertido = tratamentoMapper.dtoToEntity(tratamentoDTO);
        Tratamento tratamentoCriado = tratamentoRepository.save(tratamentoConvertido);
        TratamentoDTO tratamentoResponse = tratamentoMapper.entityToDTO(tratamentoCriado);
        EntityModel<TratamentoDTO> resource = EntityModel.of(tratamentoResponse,
                linkTo(methodOn(TratamentoController.class).getTratamentoById(tratamentoResponse.getId())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    // Lista todos os tratamentos
    @Operation(summary = "Retorna todos os tratamentos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum tratamento encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Tratamentos retornados com sucesso")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<TratamentoDTO>>> getAllTratamentos() {
        List<Tratamento> listaTratamentos = tratamentoRepository.findAll();
        if (listaTratamentos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EntityModel<TratamentoDTO>> tratamentosComLinks = listaTratamentos.stream()
                .map(tratamento -> {
                    TratamentoDTO tratamentoDTO = tratamentoMapper.entityToDTO(tratamento);
                    return EntityModel.of(tratamentoDTO,
                            linkTo(methodOn(TratamentoController.class).getTratamentoById(tratamentoDTO.getId())).withSelfRel());
                })
                .collect(Collectors.toList());
        CollectionModel<EntityModel<TratamentoDTO>> collectionModel = CollectionModel.of(tratamentosComLinks,
                linkTo(methodOn(TratamentoController.class).getAllTratamentos()).withSelfRel());
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    // Retorna um tratamento por ID
    @Operation(summary = "Retorna um tratamento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tratamento não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Tratamento encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TratamentoDTO>> getTratamentoById(@PathVariable Long id) {
        Optional<Tratamento> tratamentoSalvo = tratamentoRepository.findById(id);
        if (tratamentoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        TratamentoDTO tratamentoResponse = tratamentoMapper.entityToDTO(tratamentoSalvo.get());
        EntityModel<TratamentoDTO> resource = EntityModel.of(tratamentoResponse,
                linkTo(methodOn(TratamentoController.class).getTratamentoById(tratamentoResponse.getId())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    // Atualiza um tratamento
    @Operation(summary = "Atualiza um tratamento existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Tratamento não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Tratamento atualizado com sucesso")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<TratamentoDTO>> updateTratamento(@PathVariable Long id, @Valid @RequestBody TratamentoDTO tratamentoDTO) {
        Optional<Tratamento> tratamentoSalvo = tratamentoRepository.findById(id);
        if (tratamentoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Tratamento tratamentoAtualizado = tratamentoMapper.dtoToEntity(tratamentoDTO);
        tratamentoAtualizado.setIdTratamento(id);
        Tratamento tratamentoSalvoAtualizado = tratamentoRepository.save(tratamentoAtualizado);
        TratamentoDTO tratamentoResponse = tratamentoMapper.entityToDTO(tratamentoSalvoAtualizado);
        EntityModel<TratamentoDTO> resource = EntityModel.of(tratamentoResponse,
                linkTo(methodOn(TratamentoController.class).getTratamentoById(tratamentoResponse.getId())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    // Deleta um tratamento
    @Operation(summary = "Deleta um tratamento do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Tratamento não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Tratamento deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTratamento(@PathVariable Long id) {
        Optional<Tratamento> tratamentoSalvo = tratamentoRepository.findById(id);
        if (tratamentoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        tratamentoRepository.delete(tratamentoSalvo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
