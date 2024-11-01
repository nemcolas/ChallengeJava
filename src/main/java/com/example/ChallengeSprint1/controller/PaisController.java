package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.PaisDTO;
import com.example.ChallengeSprint1.model.Pais;
import com.example.ChallengeSprint1.repository.PaisRepository;
import com.example.ChallengeSprint1.service.PaisMapper;
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
    public ResponseEntity<PaisDTO> criarPais(@Valid @RequestBody PaisDTO paisDTO) {
        Pais paisConvertido = paisMapper.dtoToEntity(paisDTO);
        Pais paisCriado = paisRepository.save(paisConvertido);
        PaisDTO paisResponse = paisMapper.entityToDTO(paisCriado);
        return new ResponseEntity<>(paisResponse, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todos os paises cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum pais encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Paises retornados com sucesso")
    })
    public ResponseEntity<List<PaisDTO>> getAllPaiss() {
        List<Pais> listaPaises = paisRepository.findAll();
        if (listaPaises.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<PaisDTO> listaPaissResponse = listaPaises.stream()
                .map(paisMapper::entityToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listaPaissResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um pais pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pais não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Pais encontrado com sucesso")
    })
    public ResponseEntity<PaisDTO> getPaisById(@PathVariable Long id) {
        Optional<Pais> paisSalvo = paisRepository.findById(id);
        if (paisSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        PaisDTO paisResponse = paisMapper.entityToDTO(paisSalvo.get());
        return new ResponseEntity<>(paisResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um pais existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Pais não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Pais atualizado com sucesso")
    })
    public ResponseEntity<PaisDTO> updatePais(@PathVariable Long id, @Valid @RequestBody PaisDTO paisDTO) {
        Optional<Pais> paisSalvo = paisRepository.findById(id);
        if (paisSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Pais paisAtualizado = paisMapper.dtoToEntity(paisDTO);
        paisAtualizado.setCodPais(id);
        Pais paisSalvoAtualizado = paisRepository.save(paisAtualizado);
        PaisDTO paisResponse = paisMapper.entityToDTO(paisSalvoAtualizado);
        return new ResponseEntity<>(paisResponse, HttpStatus.OK);
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