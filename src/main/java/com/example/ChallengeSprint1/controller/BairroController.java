package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.BairroDTO;
import com.example.ChallengeSprint1.model.Bairro;
import com.example.ChallengeSprint1.repository.BairroRepository;
import com.example.ChallengeSprint1.mapper.BairroMapper;
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
@RequestMapping(value = "/bairro", produces = "application/json")
@Tag(name = "api-bairro")
public class BairroController {

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private BairroMapper bairroMapper;

    @PostMapping
    @Operation(summary = "Cria um bairro e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bairro cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<EntityModel<BairroDTO>> criarBairro(@Valid @RequestBody BairroDTO bairroDTO) {
        Bairro bairroConvertido = bairroMapper.dtoToEntity(bairroDTO);
        Bairro bairroCriado = bairroRepository.save(bairroConvertido);
        BairroDTO bairroResponse = bairroMapper.entityToDTO(bairroCriado);
        EntityModel<BairroDTO> resource = EntityModel.of(bairroResponse,
                linkTo(methodOn(BairroController.class).getBairroById(bairroResponse.getId())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todos os bairros cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum bairro encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Bairros retornados com sucesso")
    })
    public ResponseEntity<CollectionModel<EntityModel<BairroDTO>>> getAllBairros() {
        List<Bairro> listaBairros = bairroRepository.findAll();
        if (listaBairros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EntityModel<BairroDTO>> bairrosComLinks = listaBairros.stream()
                .map(bairro -> {
                    BairroDTO bairroDTO = bairroMapper.entityToDTO(bairro);
                    return EntityModel.of(bairroDTO,
                            linkTo(methodOn(BairroController.class).getBairroById(bairroDTO.getId())).withSelfRel());
                })
                .collect(Collectors.toList());
        CollectionModel<EntityModel<BairroDTO>> collectionModel = CollectionModel.of(bairrosComLinks,
                linkTo(methodOn(BairroController.class).getAllBairros()).withSelfRel());
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um bairro pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bairro não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Bairro encontrado com sucesso")
    })
    public ResponseEntity<EntityModel<BairroDTO>> getBairroById(@PathVariable Long id) {
        Optional<Bairro> bairroSalvo = bairroRepository.findById(id);
        if (bairroSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        BairroDTO bairroResponse = bairroMapper.entityToDTO(bairroSalvo.get());
        EntityModel<BairroDTO> resource = EntityModel.of(bairroResponse,
                linkTo(methodOn(BairroController.class).getBairroById(bairroResponse.getId())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um bairro existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bairro não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Bairro atualizado com sucesso")
    })
    public ResponseEntity<EntityModel<BairroDTO>> updateBairro(@PathVariable Long id, @Valid @RequestBody BairroDTO bairroDTO) {
        Optional<Bairro> bairroSalvo = bairroRepository.findById(id);
        if (bairroSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Bairro bairroAtualizado = bairroMapper.dtoToEntity(bairroDTO);
        bairroAtualizado.setCodBairro(id);
        Bairro bairroSalvoAtualizado = bairroRepository.save(bairroAtualizado);
        BairroDTO bairroResponse = bairroMapper.entityToDTO(bairroSalvoAtualizado);
        EntityModel<BairroDTO> resource = EntityModel.of(bairroResponse,
                linkTo(methodOn(BairroController.class).getBairroById(bairroResponse.getId())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um bairro do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bairro não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Bairro deletado com sucesso")
    })
    public ResponseEntity<Void> deleteBairro(@PathVariable Long id) {
        Optional<Bairro> bairroSalvo = bairroRepository.findById(id);
        if (bairroSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bairroRepository.delete(bairroSalvo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}