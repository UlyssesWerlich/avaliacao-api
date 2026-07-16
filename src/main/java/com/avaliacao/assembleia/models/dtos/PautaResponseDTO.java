package com.avaliacao.assembleia.models.dtos;

import com.avaliacao.assembleia.models.enums.PautaStatusEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PautaResponseDTO (
    Long id,
    String tema,
    String descricao,
    PautaStatusEnum status,
    LocalDateTime dataCriacao,
    LocalDateTime dataAberturaSessao,
    LocalDateTime dataFinalizacaoSessao
){}