package com.avaliacao.assembleia.bff.controllers;

import com.avaliacao.assembleia.bff.contracts.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/bff/mobile")
@RequiredArgsConstructor
public class MobileController {

    // PARA FACILITAÇÃO, COLOQUEI ESSA FUNCIONALIDADE NUM CONTROLLER DENTRO DA API.
    // NUM CENÁRIO DE PRODUÇÃO, CONSIDERARIA CRIAR UMA API BFF PARA COMPORTAR ESSA CAMADA.

    @Value("${api.assembleia.host}")
    private String host;

    @GetMapping("/formulario")
    public ResponseEntity<TelaDTO> formulario() {
        TelaDTO telaDTO = new TelaDTO();
        telaDTO.setTipo("FORMULARIO");
        telaDTO.setTitulo("CRIAR PAUTA");

        ItemTextoDTO itemTextoDTO = new ItemTextoDTO(
                "TEXTO",
                "Criar pauta para ser votado na assembleia pelos associados"
        );
        ItemInputTextoDTO itemInputTextoTemaDTO = new ItemInputTextoDTO(
                "INPUT_TEXTO",
                "tema",
                "Tema da Pauta",
                "Tema"
        );
        ItemInputTextoDTO itemInputTextoDescricaoDTO = new ItemInputTextoDTO(
                "INPUT_TEXTO",
                "descricao",
                "Descricao da Pauta",
                "Descricao"
        );
        BotaoDTO botaoDTO = new BotaoDTO(
                "OK",
                host + "/v1/pautas",
                Map.of(
                    "tema", "Tema",
                    "descricao", "Descricao"
                )
        );
        telaDTO.setItens(List.of(itemTextoDTO, itemInputTextoTemaDTO, itemInputTextoDescricaoDTO));
        telaDTO.setBotaoOk(botaoDTO);

        return ResponseEntity.ok(telaDTO);
    }


    @GetMapping("/selecao")
    public ResponseEntity<TelaDTO> selecao(@RequestParam Long idPauta, @RequestParam String idAssociado) {
        TelaDTO telaDTO = new TelaDTO();
        telaDTO.setTipo("SELECAO");
        telaDTO.setTitulo("VOTO");

        ItemTextoDTO itemTextoDTO = new ItemTextoDTO(
                "TEXTO",
                "Criar pauta para ser votado na assembleia pelos associados"
        );
        ItemOpcaoDTO opcaoSim =  new ItemOpcaoDTO(
                "SIM",
                host + "/v1/votos",
                Map.of(
                        "idPauta", idPauta,
                        "idAssociado", idAssociado,
                        "voto", "SIM"
                )
        );
        ItemOpcaoDTO opcaoNao =  new ItemOpcaoDTO(
                "NAO",
                host + "/v1/votos",
                Map.of(
                        "idPauta", idPauta,
                        "idAssociado", idAssociado,
                        "voto", "NAO"
                )
        );
        telaDTO.setItens(List.of(itemTextoDTO, opcaoSim, opcaoNao));

        return ResponseEntity.ok(telaDTO);
    }

}
