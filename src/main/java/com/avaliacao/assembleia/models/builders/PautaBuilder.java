package com.avaliacao.assembleia.models.builders;

import com.avaliacao.assembleia.models.dtos.PautaResponseDTO;
import com.avaliacao.assembleia.models.entities.Pauta;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PautaBuilder {

    public static PautaResponseDTO gerarResponse(Pauta pauta){
        return PautaResponseDTO.builder()
                .id(pauta.getId())
                .tema(pauta.getTema())
                .descricao(pauta.getDescricao())
                .status(pauta.getStatus())
                .dataCriacao(pauta.getDataCriacao())
                .dataAberturaSessao(pauta.getDataAberturaSessao())
                .dataFinalizacaoSessao(pauta.getDataFinalizacaoSessao())
                .build();
    }

}
