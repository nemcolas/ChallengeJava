package com.example.ChallengeSprint1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoDTO {

    @NotNull(message = "O cep é obrigatório")
    @Size(max = 8, message = "O cep não pode ter mais que 8 caracteres")
    private Long cep;

    @NotNull(message = "O id do bairro é obrigatório")
    private Long bairro;

    @NotNull(message = "O logradouro é obrigatório")
    @Size(max = 30, message = "O logradouro não pode ter mais que 30 caracteres")
    private String logradouro;

    @NotNull(message = "O número é obrigatório")
    @Size(max = 6, message = "O número não pode ter mais que 6 caracteres")
    private Long numero;

    @NotNull(message = "A referência é obrigatória")
    @Size(max = 30, message = "A referência não pode ter mais que 30 caracteres")
    private String referencia;

}
