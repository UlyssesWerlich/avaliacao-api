package com.avaliacao.assembleia.handler;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {

    ERRO_PAUTA_NAO_ENCONTRADA("erro.pauta.nao.encontrada"),
    ERRO_INICIAR_VOTACAO_PAUTA_NAO_ENCONTRADA("erro.iniciar.votacao.pauta.nao.encontrada"),
    ERRO_VOTO_JA_FEITO_PARA_PAUTA_E_ASSOCIADO("erro.voto.ja.feito.para.pauta.e.associado"),
    ERRO_VOTO_PAUTA_NAO_ENCONTRADA("erro.voto.pauta.nao.encontrada"),
    ERRO_VOTO_PAUTA_JA_ENCERRADA("erro.voto.pauta.ja.encerrada"),
    ERRO_VOTO_CPF_INVALIDO("erro.voto.cpf.invalido");

    final String valor;

    ErrorCodeEnum(String valor) {
        this.valor = valor;
    }
}
