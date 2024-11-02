package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.PaisDTO;
import com.example.ChallengeSprint1.model.Pais;
import com.example.ChallengeSprint1.repository.PaisRepository;
import com.example.ChallengeSprint1.service.mapper.PaisMapper;
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
@RequestMapping(value = "/pais", produces = "application/json")
@Tag(name = "api-pais")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private PaisMapper paisMapper;

    @PostMapping
    @Operation(summary = "Cria um pais e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pais cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<EntityModel<Pais>> criarPais(@Valid @RequestBody PaisDTO paisDTO) {
        Pais paisConvertido = paisMapper.dtoToEntity(paisDTO);
        Pais paisCriado = paisRepository.save(paisConvertido);
        EntityModel<Pais> resource = EntityModel.of(paisCriado,
                linkTo(methodOn(PaisController.class).getPaisById(paisCriado.getCodPais())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todos os paises cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum pais encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Paises retornados com sucesso")
    })
    public ResponseEntity<CollectionModel<EntityModel<Pais>>> getAllPaises() {
        List<Pais> listaPaises = paisRepository.findAll();
        if (listaPaises.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EntityModel<Pais>> paisesComLinks = listaPaises.stream()
                .map(pais -> EntityModel.of(pais,
                            linkTo(methodOn(PaisController.class).getPaisById(pais.getCodPais())).withSelfRel())
                )
                .collect(Collectors.toList());
        CollectionModel<EntityModel<Pais>> collectionModel = CollectionModel.of(paisesComLinks,
                linkTo(methodOn(PaisController.class).getAllPaises()).withSelfRel());
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um pais pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pais não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Pais encontrado com sucesso")
    })
    public ResponseEntity<EntityModel<Pais>> getPaisById(@PathVariable Long id) {
        Optional<Pais> paisSalvo = paisRepository.findById(id);
        if (paisSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Pais pais = paisSalvo.get();
        EntityModel<Pais> resource = EntityModel.of(pais,
                linkTo(methodOn(PaisController.class).getPaisById(id)).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um pais existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Pais não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Pais atualizado com sucesso")
    })
    public ResponseEntity<EntityModel<Pais>> updatePais(@PathVariable Long id, @Valid @RequestBody PaisDTO paisDTO) {
        Optional<Pais> paisSalvo = paisRepository.findById(id);
        if (paisSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Pais paisAtualizado = paisMapper.dtoToEntity(paisDTO);
        paisAtualizado.setCodPais(id);
        Pais paisSalvoAtualizado = paisRepository.save(paisAtualizado);
        EntityModel<Pais> resource = EntityModel.of(paisSalvoAtualizado,
                linkTo(methodOn(PaisController.class).getPaisById(paisSalvoAtualizado.getCodPais())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um pais do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Pais não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Pais deletado com sucesso")
    })
    public ResponseEntity<Void> deletePais(@PathVariable Long id) {
        Optional<Pais> paisSalvo = paisRepository.findById(id);
        if (paisSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        paisRepository.delete(paisSalvo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}