package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.GeneroDTO;
import com.example.ChallengeSprint1.model.Genero;
import com.example.ChallengeSprint1.repository.GeneroRepository;
import com.example.ChallengeSprint1.service.GeneroMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<GeneroDTO> criarGenero(@Valid @RequestBody GeneroDTO generoDTO) {
        Genero generoConvertido = generoMapper.dtoToEntity(generoDTO);
        Genero generoCriado = generoRepository.save(generoConvertido);
        GeneroDTO generoResponse = generoMapper.entityToDTO(generoCriado);
        return new ResponseEntity<>(generoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todos os generos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum genero encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Generos retornados com sucesso")
    })
    public ResponseEntity<List<GeneroDTO>> getAllGeneros() {
        List<Genero> listaGeneros = generoRepository.findAll();
        if (listaGeneros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<GeneroDTO> listaGenerosResponse = listaGeneros.stream()
                .map(generoMapper::entityToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listaGenerosResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um genero pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Genero não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Genero encontrado com sucesso")
    })
    public ResponseEntity<GeneroDTO> getGeneroById(@PathVariable Long id) {
        Optional<Genero> generoSalvo = generoRepository.findById(id);
        if (generoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        GeneroDTO generoResponse = generoMapper.entityToDTO(generoSalvo.get());
        return new ResponseEntity<>(generoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um genero existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Genero não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Genero atualizado com sucesso")
    })
    public ResponseEntity<GeneroDTO> updateGenero(@PathVariable Long id, @Valid @RequestBody GeneroDTO generoDTO) {
        Optional<Genero> generoSalvo = generoRepository.findById(id);
        if (generoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Genero generoAtualizado = generoMapper.dtoToEntity(generoDTO);
        generoAtualizado.setIdGenero(id);
        Genero generoSalvoAtualizado = generoRepository.save(generoAtualizado);
        GeneroDTO generoResponse = generoMapper.entityToDTO(generoSalvoAtualizado);
        return new ResponseEntity<>(generoResponse, HttpStatus.OK);
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