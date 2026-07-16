package com.avaliacao.assembleia.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PautaRequestDTO (
    @NotBlank
    String tema
){}