package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.GeneroDTO;
import com.example.ChallengeSprint1.model.Genero;
import com.example.ChallengeSprint1.repository.GeneroRepository;
import com.example.ChallengeSprint1.service.mapper.GeneroMapper;
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
@RequestMapping(value = "/genero", produces = "application/json")
@Tag(name = "api-genero")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private GeneroMapper generoMapper;

    @PostMapping
    @Operation(summary = "Cria um genero e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genero cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<EntityModel<Genero>> criarGenero(@Valid @RequestBody GeneroDTO generoDTO) {
        Genero generoConvertido = generoMapper.dtoToEntity(generoDTO);
        Genero generoCriado = generoRepository.save(generoConvertido);
        EntityModel<Genero> resource = EntityModel.of(generoCriado,
                linkTo(methodOn(GeneroController.class).getGeneroById(generoCriado.getIdGenero())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todos os generos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum genero encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Generos retornados com sucesso")
    })
    public ResponseEntity<CollectionModel<EntityModel<Genero>>> getAllGeneros() {
        List<Genero> listaGeneros = generoRepository.findAll();
        if (listaGeneros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EntityModel<Genero>> generosComLinks = listaGeneros.stream()
                .map(genero -> EntityModel.of(genero,
                            linkTo(methodOn(GeneroController.class).getGeneroById(genero.getIdGenero())).withSelfRel())
                )
                .collect(Collectors.toList());
        CollectionModel<EntityModel<Genero>> collectionModel = CollectionModel.of(generosComLinks,
                linkTo(methodOn(GeneroController.class).getAllGeneros()).withSelfRel());
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um genero pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Genero não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Genero encontrado com sucesso")
    })
    public ResponseEntity<EntityModel<Genero>> getGeneroById(@PathVariable Long id) {
        Optional<Genero> generoSalvo = generoRepository.findById(id);
        if (generoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Genero genero = generoSalvo.get();
        EntityModel<Genero> resource = EntityModel.of(genero,
                linkTo(methodOn(GeneroController.class).getGeneroById(id)).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um genero existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Genero não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Genero atualizado com sucesso")
    })
    public ResponseEntity<EntityModel<Genero>> updateGenero(@PathVariable Long id, @Valid @RequestBody GeneroDTO generoDTO) {
        Optional<Genero> generoSalvo = generoRepository.findById(id);
        if (generoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Genero generoAtualizado = generoMapper.dtoToEntity(generoDTO);
        generoAtualizado.setIdGenero(id);
        Genero generoSalvoAtualizado = generoRepository.save(generoAtualizado);
        EntityModel<Genero> resource = EntityModel.of(generoSalvoAtualizado,
                linkTo(methodOn(GeneroController.class).getGeneroById(generoSalvoAtualizado.getIdGenero())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um genero do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Genero não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Genero deletado com sucesso")
    })
    public ResponseEntity<Void> deleteGenero(@PathVariable Long id) {
        Optional<Genero> generoSalvo = generoRepository.findById(id);
        if (generoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        generoRepository.delete(generoSalvo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}