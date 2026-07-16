package com.avaliacao.assembleia.models.dtos;

import com.avaliacao.assembleia.models.enums.OpcaoVotoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VotoRequestDTO(
        @NotNull Long idPauta,
        @NotBlank String idAssociado,
        @NotNull OpcaoVotoEnum voto
) {
}
