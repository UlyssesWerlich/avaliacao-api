package com.avaliacao.assembleia.controllers.v1;

import com.avaliacao.assembleia.models.dtos.PautaRequestDTO;
import com.avaliacao.assembleia.models.dtos.PautaResponseDTO;
import com.avaliacao.assembleia.services.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/pautas")
@RequiredArgsConstructor
public class PautaController {

    private final PautaService pautaService;


    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar pauta",
            description = "Busca pauta por identificador único"
    )
    public ResponseEntity<PautaResponseDTO> buscarPauta(@PathVariable Long id) {
        return ResponseEntity.ok(pautaService.buscarPauta(id));
    }


    @GetMapping
    @Operation(
            summary = "Listar pautas",
            description = "Lista pautas por parâmetros"
    )
    public ResponseEntity<Page<PautaResponseDTO>> listarPautas(
            @RequestParam String tema,
            Pageable pagina
    ) {
        return ResponseEntity.ok(pautaService.listarPautas(tema, pagina));
    }


    @PostMapping
    @Operation(
            summary = "Criar pauta",
            description = "Cria uma nova pauta"
    )
    public ResponseEntity<PautaResponseDTO> criarPauta(@RequestBody @Valid PautaRequestDTO pautaRequestDTO){
        return ResponseEntity.ok(pautaService.criarPauta(pautaRequestDTO));
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Iniciar pauta",
            description = "Dá o início para a votação de uma pauta"
    )
    public ResponseEntity<PautaResponseDTO> iniciarPauta(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "1") Integer minutosDeVotacao
    ) {
        return ResponseEntity.ok(pautaService.iniciarPauta(id, minutosDeVotacao));
    }


    @PutMapping("/{id}/finalizar")
    @Operation(
            summary = "Finalizar pauta",
            description = "Encerra a votação de uma pauta antes do tempo determinado"
    )
    public ResponseEntity<Void> finalizarPauta(
            @PathVariable Long id
    ) {
        pautaService.finalizarPauta(id);
        return ResponseEntity.ok().build();
    }
}
