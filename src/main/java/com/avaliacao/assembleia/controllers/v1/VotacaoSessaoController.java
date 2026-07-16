package com.avaliacao.assembleia.controllers.v1;

import com.avaliacao.assembleia.models.dtos.ContagemVotosDTO;
import com.avaliacao.assembleia.models.dtos.VotoRequestDTO;
import com.avaliacao.assembleia.services.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/votos")
@RequiredArgsConstructor
public class VotacaoSessaoController {

    private final VotoService votoService;

    @PostMapping
    @Operation(
            summary = "Registrar voto",
            description = "Registra o voto por pauta e associado"
    )
    public ResponseEntity<Void> votar(@RequestBody @Valid VotoRequestDTO votoRequest) {
        votoService.votar(votoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    @Operation(
            summary = "Buscar contabilidade votos",
            description = "Gera a contabilidade dos votos por pauta"
    )
    public ResponseEntity<ContagemVotosDTO> contabilizarVotos(final Long idPauta){
        return ResponseEntity.ok(votoService.contabilizarVotos(idPauta));
    }

}
