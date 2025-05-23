package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.CidadeDTO;
import com.example.ChallengeSprint1.model.Cidade;
import com.example.ChallengeSprint1.repository.CidadeRepository;
import com.example.ChallengeSprint1.service.mapper.CidadeMapper;
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
@RequestMapping(value = "/cidade", produces = "application/json")
@Tag(name = "api-cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeMapper cidadeMapper;

    @PostMapping
    @Operation(summary = "Cria uma cidade e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cidade cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<EntityModel<Cidade>> criarCidade(@Valid @RequestBody CidadeDTO cidadeDTO) {
        Cidade cidadeConvertida = cidadeMapper.dtoToEntity(cidadeDTO);
        Cidade cidadeCriada = cidadeRepository.save(cidadeConvertida);
        EntityModel<Cidade> resource = EntityModel.of(cidadeCriada,
                linkTo(methodOn(CidadeController.class).getCidadeById(cidadeCriada.getCodCidade())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todas as cidades cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhuma cidade encontrada",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Cidades retornadas com sucesso")
    })
    public ResponseEntity<CollectionModel<EntityModel<Cidade>>> getAllCidades() {
        List<Cidade> listaCidades = cidadeRepository.findAll();
        if (listaCidades.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EntityModel<Cidade>> cidadesComLinks = listaCidades.stream()
                .map(cidade -> EntityModel.of(cidade,
                            linkTo(methodOn(CidadeController.class).getCidadeById(cidade.getCodCidade())).withSelfRel())
                )
                .collect(Collectors.toList());
        CollectionModel<EntityModel<Cidade>> collectionModel = CollectionModel.of(cidadesComLinks,
                linkTo(methodOn(CidadeController.class).getAllCidades()).withSelfRel());
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma cidade pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cidade não encontrada",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Cidade encontrada com sucesso")
    })
    public ResponseEntity<EntityModel<Cidade>> getCidadeById(@PathVariable Long id) {
        Optional<Cidade> cidadeSalva = cidadeRepository.findById(id);
        if (cidadeSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Cidade cidade = cidadeSalva.get();
        EntityModel<Cidade> resource = EntityModel.of(cidade,
                linkTo(methodOn(CidadeController.class).getCidadeById(id)).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma cidade existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Cidade não encontrada ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Cidade atualizada com sucesso")
    })
    public ResponseEntity<EntityModel<Cidade>> updateCidade(@PathVariable Long id, @Valid @RequestBody CidadeDTO cidadeDTO) {
        Optional<Cidade> cidadeSalva = cidadeRepository.findById(id);
        if (cidadeSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Cidade cidadeAtualizada = cidadeMapper.dtoToEntity(cidadeDTO);
        cidadeAtualizada.setCodCidade(id);
        Cidade cidadeSalvaAtualizada = cidadeRepository.save(cidadeAtualizada);
        EntityModel<Cidade> resource = EntityModel.of(cidadeSalvaAtualizada,
                linkTo(methodOn(CidadeController.class).getCidadeById(cidadeSalvaAtualizada.getCodCidade())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma cidade do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Cidade não encontrada",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Cidade deletada com sucesso")
    })
    public ResponseEntity<Void> deleteCidade(@PathVariable Long id) {
        Optional<Cidade> cidadeSalva = cidadeRepository.findById(id);
        if (cidadeSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cidadeRepository.delete(cidadeSalva.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}