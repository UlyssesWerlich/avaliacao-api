package com.avaliacao.assembleia.services;

import com.avaliacao.assembleia.models.dtos.PautaRequestDTO;
import com.avaliacao.assembleia.models.dtos.PautaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PautaService {

    PautaResponseDTO buscarPauta(Long id);

    Page<PautaResponseDTO> listarPautas(String tema, Pageable pagina);

    PautaResponseDTO criarPauta(PautaRequestDTO pautaRequestDTO);

    PautaResponseDTO iniciarPauta(Long id, Integer minutosDeVotacao);

    void finalizarPauta(Long id);

}
