package com.avaliacao.assembleia.bff.contracts;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TelaDTO {

    private String tipo;
    private String titulo;
    private List<Item> itens;
    private BotaoDTO botaoOk;
    private BotaoDTO botaoCancelar;

}
