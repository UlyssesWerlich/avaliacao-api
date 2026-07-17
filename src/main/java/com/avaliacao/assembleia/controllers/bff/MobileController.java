package com.avaliacao.assembleia.controllers.bff;

import com.avaliacao.assembleia.models.dtos.PautaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pautas")
@RequiredArgsConstructor
public class MobileController {



//    @GetMapping("/{id}")
//    @Operation(
//            summary = "Buscar pauta",
//            description = "Busca pauta por identificador único"
//    )
//    public ResponseEntity<PautaResponseDTO> buscarPauta(@PathVariable Long id) {
//
//
//
//        return ResponseEntity.ok(pautaService.buscarPauta(id));
//    }
//
//
//    @GetMapping("/{id}")
//    @Operation(
//            summary = "Buscar pauta",
//            description = "Busca pauta por identificador único"
//    )
//    public ResponseEntity<PautaResponseDTO> buscarPauta(@PathVariable Long id) {
//
//
//        return ResponseEntity.ok(pautaService.buscarPauta(id));
//    }
//
//
//    public String tela1(){
//        return """
//            {
//                "tipo" :"FORMULARIO",
//                "titulo": "TITULO TELA",
//                "itens" : [{
//                    "tipo": "TEXTO",
//                    "texto": "Texto"
//                }, {
//                    "tipo": "INPUT_TEXTO",
//                    "id": "idCampoTexto",
//                    "titulo": "Campo de Texto",
//                    "valor": "Texto"
//                }, {
//                    "tipo": "INPUT_TEXTO",
//                    "id": "idCampoTexto",
//                    "titulo": "Campo de Texto",
//                    "valor": "Texto"
//                }],
//
//            """;
//
//    }

}
