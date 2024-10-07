package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.DentistaDTO;
import com.example.ChallengeSprint1.model.Dentista;
import com.example.ChallengeSprint1.repository.DentistaRepository;
import com.example.ChallengeSprint1.service.DentistaMapper;
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
    public ResponseEntity<DentistaDTO> criarDentista(@Valid @RequestBody DentistaDTO dentistaDTO) {
        Dentista dentistaConvertido = dentistaMapper.dtoToEntity(dentistaDTO);
        Dentista dentistaCriado = dentistaRepository.save(dentistaConvertido);
        DentistaDTO dentistaResponse = dentistaMapper.entityToDTO(dentistaCriado);
        return new ResponseEntity<>(dentistaResponse, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todos os dentistas cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum dentista encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Dentistas retornados com sucesso")
    })
    public ResponseEntity<List<DentistaDTO>> getAllDentistas() {
        List<Dentista> listaDentistas = dentistaRepository.findAll();
        if (listaDentistas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<DentistaDTO> listaDentistasResponse = listaDentistas.stream()
                .map(dentistaMapper::entityToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listaDentistasResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um dentista pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dentista não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Dentista encontrado com sucesso")
    })
    public ResponseEntity<DentistaDTO> getDentistaById(@PathVariable Long id) {
        Optional<Dentista> dentistaSalvo = dentistaRepository.findById(id);
        if (dentistaSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        DentistaDTO dentistaResponse = dentistaMapper.entityToDTO(dentistaSalvo.get());
        return new ResponseEntity<>(dentistaResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um dentista existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Dentista não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Dentista atualizado com sucesso")
    })
    public ResponseEntity<DentistaDTO> updateDentista(@PathVariable Long id, @Valid @RequestBody DentistaDTO dentistaDTO) {
        Optional<Dentista> dentistaSalvo = dentistaRepository.findById(id);
        if (dentistaSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dentista dentistaAtualizado = dentistaMapper.dtoToEntity(dentistaDTO);
        dentistaAtualizado.setIdDentista(id);
        Dentista dentistaSalvoAtualizado = dentistaRepository.save(dentistaAtualizado);
        DentistaDTO dentistaResponse = dentistaMapper.entityToDTO(dentistaSalvoAtualizado);
        return new ResponseEntity<>(dentistaResponse, HttpStatus.OK);
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