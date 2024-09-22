package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.PacienteDTO;
import com.example.ChallengeSprint1.model.Paciente;
import com.example.ChallengeSprint1.repository.PacienteRepository;
import com.example.ChallengeSprint1.service.PacienteMapper;
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

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pacientes", produces = {"application/json"})
@Tag(name = "api-pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteMapper pacienteMapper;

    // Cria um novo paciente
    @Operation(summary = "Cria um paciente e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<PacienteDTO> createPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        Paciente pacienteConvertido = pacienteMapper.dtoToEntity(pacienteDTO);
        Paciente pacienteCriado = pacienteRepository.save(pacienteConvertido);
        PacienteDTO pacienteResponse;
        pacienteResponse = pacienteMapper.entityToDTO(pacienteCriado);
        return new ResponseEntity<>(pacienteResponse, HttpStatus.CREATED);
    }

    // Lista todos os pacientes
    @Operation(summary = "Retorna todos os pacientes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum paciente encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Pacientes retornados com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        if (listaPacientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<PacienteDTO> listaPacientesResponse;
        try {
            listaPacientesResponse = listaPacientes.stream()
                    .map(paciente -> {
                        return pacienteMapper.entityToDTO(paciente);
                    })
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(listaPacientesResponse, HttpStatus.OK);
    }

    // Retorna um paciente por ID
    @Operation(summary = "Retorna um paciente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
        Optional<Paciente> pacienteSalvo = pacienteRepository.findById(id);
        if (pacienteSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        PacienteDTO pacienteResponse;
        pacienteResponse = pacienteMapper.entityToDTO(pacienteSalvo.get());
        return new ResponseEntity<>(pacienteResponse, HttpStatus.OK);
    }

    // Atualiza um paciente
    @Operation(summary = "Atualiza um paciente existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Paciente não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Long id, @Valid @RequestBody PacienteDTO pacienteDTO) {
        Optional<Paciente> pacienteSalvo = pacienteRepository.findById(id);
        if (pacienteSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Paciente pacienteAtualizado = pacienteMapper.dtoToEntity(pacienteDTO);
        pacienteAtualizado.setIdPaciente(id);
        Paciente pacienteSalvoAtualizado = pacienteRepository.save(pacienteAtualizado);
        PacienteDTO pacienteResponse;
        pacienteResponse = pacienteMapper.entityToDTO(pacienteSalvoAtualizado);
        return new ResponseEntity<>(pacienteResponse, HttpStatus.OK);
    }

    // Deleta um paciente
    @Operation(summary = "Deleta um paciente do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Paciente não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Paciente deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        Optional<Paciente> pacienteSalvo = pacienteRepository.findById(id);
        if (pacienteSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        pacienteRepository.delete(pacienteSalvo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}