package com.avaliacao.assembleia.services.impl;

import com.avaliacao.assembleia.handler.BusinessException;
import com.avaliacao.assembleia.handler.ErrorCodeEnum;
import com.avaliacao.assembleia.models.builders.PautaBuilder;
import com.avaliacao.assembleia.models.dtos.PautaRequestDTO;
import com.avaliacao.assembleia.models.dtos.PautaResponseDTO;
import com.avaliacao.assembleia.models.entities.Pauta;
import com.avaliacao.assembleia.models.enums.PautaStatusEnum;
import com.avaliacao.assembleia.repositories.PautaRepository;
import com.avaliacao.assembleia.services.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;


    @Override
    public PautaResponseDTO buscarPauta(Long id) {
        return pautaRepository.findById(id)
                .map(PautaBuilder::gerarResponse)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, ErrorCodeEnum.ERRO_PAUTA_NAO_ENCONTRADA, id));
    }


    @Override
    public Page<PautaResponseDTO> listarPautas(String tema, Pageable pagina) {
        return pautaRepository.findAll(pagina)
                .map(PautaBuilder::gerarResponse);
    }


    @Override
    public void criarPauta(PautaRequestDTO pautaRequestDTO) {
        Pauta pauta = new Pauta();
        pauta.setTema(pautaRequestDTO.tema());
        pauta.setStatus(PautaStatusEnum.CRIADA);
        pauta.setDataCriacao(LocalDateTime.now());
        pautaRepository.save(pauta);
    }


    @Override
    public void iniciarPauta(Long id, Integer minutosDeVotacao) {

        Pauta pauta = pautaRepository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, ErrorCodeEnum.ERRO_INICIAR_VOTACAO_PAUTA_NAO_ENCONTRADA, id));

        pauta.setStatus(PautaStatusEnum.INICIADA);
        pauta.setDataAberturaSessao(LocalDateTime.now());
        pauta.setDataFinalizacaoSessao(LocalDateTime.now().plusMinutes(minutosDeVotacao));
        pautaRepository.save(pauta);
    }


    @Override
    public void finalizarPauta() {

    }
}
