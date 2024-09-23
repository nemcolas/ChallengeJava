package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.ConsultaDTO;
import com.example.ChallengeSprint1.model.Consulta;
import com.example.ChallengeSprint1.repository.ConsultaRepository;
import com.example.ChallengeSprint1.service.ConsultaMapper;
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
@RequestMapping(value = "/consulta", produces = "application/json")
@Tag(name = "api-consulta")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ConsultaMapper consultaMapper;

    @PostMapping
    @Operation(summary = "Cria uma consulta e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consulta cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<ConsultaDTO> criarConsulta(@Valid @RequestBody ConsultaDTO consultaDTO) {
        Consulta consultaConvertida = consultaMapper.dtoToEntity(consultaDTO);
        Consulta consultaCriada = consultaRepository.save(consultaConvertida);
        ConsultaDTO consultaResponse = consultaMapper.entityToDTO(consultaCriada);
        return new ResponseEntity<>(consultaResponse, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todas as consultas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhuma consulta encontrada",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Consultas retornadas com sucesso")
    })
    public ResponseEntity<List<ConsultaDTO>> getAllConsultas() {
        List<Consulta> listaConsultas = consultaRepository.findAll();
        if (listaConsultas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<ConsultaDTO> listaConsultasResponse = listaConsultas.stream()
                .map(consultaMapper::entityToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listaConsultasResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma consulta pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Consulta não encontrada",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Consulta encontrada com sucesso")
    })
    public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable Long id) {
        Optional<Consulta> consultaSalva = consultaRepository.findById(id);
        if (consultaSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ConsultaDTO consultaResponse = consultaMapper.entityToDTO(consultaSalva.get());
        return new ResponseEntity<>(consultaResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma consulta existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Consulta não encontrada ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso")
    })
    public ResponseEntity<ConsultaDTO> updateConsulta(@PathVariable Long id, @Valid @RequestBody ConsultaDTO consultaDTO) {
        Optional<Consulta> consultaSalva = consultaRepository.findById(id);
        if (consultaSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Consulta consultaAtualizada = consultaMapper.dtoToEntity(consultaDTO);
        consultaAtualizada.setIdConsulta(id);
        Consulta consultaSalvaAtualizada = consultaRepository.save(consultaAtualizada);
        ConsultaDTO consultaResponse = consultaMapper.entityToDTO(consultaSalvaAtualizada);
        return new ResponseEntity<>(consultaResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma consulta do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Consulta não encontrada",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Consulta deletada com sucesso")
    })
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        Optional<Consulta> consultaSalva = consultaRepository.findById(id);
        if (consultaSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        consultaRepository.delete(consultaSalva.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}