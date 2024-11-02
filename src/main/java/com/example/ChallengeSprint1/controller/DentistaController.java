package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.DentistaDTO;
import com.example.ChallengeSprint1.model.Dentista;
import com.example.ChallengeSprint1.repository.DentistaRepository;
import com.example.ChallengeSprint1.service.mapper.DentistaMapper;
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
@RequestMapping(value = "/dentista", produces = "application/json")
@Tag(name = "api-dentista")
public class DentistaController {

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private DentistaMapper dentistaMapper;

    @PostMapping
    @Operation(summary = "Cria um dentista e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dentista cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<EntityModel<Dentista>> criarDentista(@Valid @RequestBody DentistaDTO dentistaDTO) {
        Dentista dentistaConvertido = dentistaMapper.dtoToEntity(dentistaDTO);
        Dentista dentistaCriado = dentistaRepository.save(dentistaConvertido);
        EntityModel<Dentista> resource = EntityModel.of(dentistaCriado,
                linkTo(methodOn(DentistaController.class).getDentistaById(dentistaCriado.getIdDentista())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todos os dentistas cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum dentista encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Dentistas retornados com sucesso")
    })
    public ResponseEntity<CollectionModel<EntityModel<Dentista>>> getAllDentistas() {
        List<Dentista> listaDentistas = dentistaRepository.findAll();
        if (listaDentistas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EntityModel<Dentista>> dentistasComLinks = listaDentistas.stream()
                .map(dentista -> EntityModel.of(dentista,
                            linkTo(methodOn(DentistaController.class).getDentistaById(dentista.getIdDentista())).withSelfRel())
                )
                .collect(Collectors.toList());
        CollectionModel<EntityModel<Dentista>> collectionModel = CollectionModel.of(dentistasComLinks,
                linkTo(methodOn(DentistaController.class).getAllDentistas()).withSelfRel());
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um dentista pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dentista não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Dentista encontrado com sucesso")
    })
    public ResponseEntity<EntityModel<Dentista>> getDentistaById(@PathVariable Long id) {
        Optional<Dentista> dentistaSalvo = dentistaRepository.findById(id);
        if (dentistaSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Dentista dentista = dentistaSalvo.get();
        EntityModel<Dentista> resource = EntityModel.of(dentista,
                linkTo(methodOn(DentistaController.class).getDentistaById(id)).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um dentista existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Dentista não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Dentista atualizado com sucesso")
    })
    public ResponseEntity<EntityModel<Dentista>> updateDentista(@PathVariable Long id, @Valid @RequestBody DentistaDTO dentistaDTO) {
        Optional<Dentista> dentistaSalvo = dentistaRepository.findById(id);
        if (dentistaSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dentista dentistaAtualizado = dentistaMapper.dtoToEntity(dentistaDTO);
        dentistaAtualizado.setIdDentista(id);
        Dentista dentistaSalvoAtualizado = dentistaRepository.save(dentistaAtualizado);
        EntityModel<Dentista> resource = EntityModel.of(dentistaSalvoAtualizado,
                linkTo(methodOn(DentistaController.class).getDentistaById(dentistaSalvoAtualizado.getIdDentista())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um dentista do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Dentista não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Dentista deletado com sucesso")
    })
    public ResponseEntity<Void> deleteDentista(@PathVariable Long id) {
        Optional<Dentista> dentistaSalvo = dentistaRepository.findById(id);
        if (dentistaSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dentistaRepository.delete(dentistaSalvo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}