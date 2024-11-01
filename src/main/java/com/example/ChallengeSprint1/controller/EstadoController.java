package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.EstadoDTO;
import com.example.ChallengeSprint1.model.Estado;
import com.example.ChallengeSprint1.repository.EstadoRepository;
import com.example.ChallengeSprint1.service.EstadoMapper;
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
@RequestMapping(value = "/estado", produces = "application/json")
@Tag(name = "api-estado")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoMapper estadoMapper;

    @PostMapping
    @Operation(summary = "Cria um estado e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estado cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<EstadoDTO> criarEstado(@Valid @RequestBody EstadoDTO estadoDTO) {
        Estado estadoConvertido = estadoMapper.dtoToEntity(estadoDTO);
        Estado estadoCriado = estadoRepository.save(estadoConvertido);
        EstadoDTO estadoResponse = estadoMapper.entityToDTO(estadoCriado);
        return new ResponseEntity<>(estadoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todos os estados cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nenhum estado encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Estados retornados com sucesso")
    })
    public ResponseEntity<List<EstadoDTO>> getAllEstados() {
        List<Estado> listaEstados = estadoRepository.findAll();
        if (listaEstados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EstadoDTO> listaEstadosResponse = listaEstados.stream()
                .map(estadoMapper::entityToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listaEstadosResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um estado pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estado não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Estado encontrado com sucesso")
    })
    public ResponseEntity<EstadoDTO> getEstadoById(@PathVariable Long id) {
        Optional<Estado> estadoSalvo = estadoRepository.findById(id);
        if (estadoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        EstadoDTO estadoResponse = estadoMapper.entityToDTO(estadoSalvo.get());
        return new ResponseEntity<>(estadoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um estado existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Estado não encontrado ou atributos inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Estado atualizado com sucesso")
    })
    public ResponseEntity<EstadoDTO> updateEstado(@PathVariable Long id, @Valid @RequestBody EstadoDTO estadoDTO) {
        Optional<Estado> estadoSalvo = estadoRepository.findById(id);
        if (estadoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Estado estadoAtualizado = estadoMapper.dtoToEntity(estadoDTO);
        estadoAtualizado.setCodEstado(id);
        Estado estadoSalvoAtualizado = estadoRepository.save(estadoAtualizado);
        EstadoDTO estadoResponse = estadoMapper.entityToDTO(estadoSalvoAtualizado);
        return new ResponseEntity<>(estadoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um estado do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Estado não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Estado deletado com sucesso")
    })
    public ResponseEntity<Void> deleteEstado(@PathVariable Long id) {
        Optional<Estado> estadoSalvo = estadoRepository.findById(id);
        if (estadoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        estadoRepository.delete(estadoSalvo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}