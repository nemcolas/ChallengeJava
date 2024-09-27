package com.example.ChallengeSprint1.controller;


import com.example.ChallengeSprint1.dto.SinistroDTO;
import com.example.ChallengeSprint1.model.Sinistro;
import com.example.ChallengeSprint1.repository.SinistroRepository;
import com.example.ChallengeSprint1.service.SinistroMapper;
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
@RequestMapping(value = "/sinistros", produces = {"application/json"})
@Tag(name = "api-sinistros")
public class SinistroController {

    @Autowired
    private SinistroRepository sinistroRepository;

    @Autowired
    private SinistroMapper sinistroMapper;

    // Cria um novo sinistro
    @Operation(summary = "Cria um sinistro e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sinistro cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<SinistroDTO> createSinistro(@Valid @RequestBody SinistroDTO sinistroDTO) {
        Sinistro sinistroConvertido = sinistroMapper.dtoToEntity(sinistroDTO);
        Sinistro sinistroCriado = sinistroRepository.save(sinistroConvertido);
        SinistroDTO sinistroResponse = sinistroMapper.entityToDTO(sinistroCriado);
        return new ResponseEntity<>(sinistroResponse, HttpStatus.CREATED);
    }

    // Lista todos os sinistros
    @Operation(summary = "Retorna todos os sinistros cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum sinistro encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Sinistros retornados com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<SinistroDTO>> getAllSinistros() {
        List<Sinistro> listaSinistros = sinistroRepository.findAll();
        if (listaSinistros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<SinistroDTO> listaSinistrosResponse = listaSinistros.stream()
                .map(sinistroMapper::entityToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listaSinistrosResponse, HttpStatus.OK);
    }

    // Retorna um sinistro por ID
    @Operation(summary = "Retorna um sinistro pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sinistro não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Sinistro encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SinistroDTO> getSinistroById(@PathVariable Long id) {
        Optional<Sinistro> sinistroSalvo = sinistroRepository.findById(id);
        if (sinistroSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        SinistroDTO sinistroResponse = sinistroMapper.entityToDTO(sinistroSalvo.get());
        return new ResponseEntity<>(sinistroResponse, HttpStatus.OK);
    }

    // Atualiza um sinistro
    @Operation(summary = "Atualiza um sinistro existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Sinistro não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Sinistro atualizado com sucesso")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SinistroDTO> updateSinistro(@PathVariable Long id, @Valid @RequestBody SinistroDTO sinistroDTO) {
        Optional<Sinistro> sinistroSalvo = sinistroRepository.findById(id);
        if (sinistroSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Sinistro sinistroAtualizado = sinistroMapper.dtoToEntity(sinistroDTO);
        sinistroAtualizado.setIdSinistro(id);
        Sinistro sinistroSalvoAtualizado = sinistroRepository.save(sinistroAtualizado);
        SinistroDTO sinistroResponse = sinistroMapper.entityToDTO(sinistroSalvoAtualizado);
        return new ResponseEntity<>(sinistroResponse, HttpStatus.OK);
    }

    // Deleta um sinistro
    @Operation(summary = "Deleta um sinistro do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Sinistro não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Sinistro deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSinistro(@PathVariable Long id) {
        Optional<Sinistro> sinistroSalvo = sinistroRepository.findById(id);
        if (sinistroSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sinistroRepository.delete(sinistroSalvo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}