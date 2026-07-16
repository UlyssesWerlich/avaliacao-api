package com.avaliacao.assembleia.services.impl;

import com.avaliacao.assembleia.handler.BusinessException;
import com.avaliacao.assembleia.handler.ErrorCodeEnum;
import com.avaliacao.assembleia.models.builders.VotoBuilder;
import com.avaliacao.assembleia.models.dtos.ContagemVotosDTO;
import com.avaliacao.assembleia.models.dtos.VotoRequestDTO;
import com.avaliacao.assembleia.models.entities.Pauta;
import com.avaliacao.assembleia.models.enums.OpcaoVotoEnum;
import com.avaliacao.assembleia.models.enums.PautaStatusEnum;
import com.avaliacao.assembleia.repositories.PautaRepository;
import com.avaliacao.assembleia.repositories.VotoRepository;
import com.avaliacao.assembleia.services.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final PautaRepository pautaRepository;


    public void votar(final VotoRequestDTO votoRequest) {

        Pauta pauta = pautaRepository.findByIdAndStatus(votoRequest.idPauta(), PautaStatusEnum.INICIADA)
                .orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.ERRO_VOTO_PAUTA_NAO_ENCONTRADA));

        if (pauta.getDataFinalizacaoSessao().isBefore(LocalDateTime.now())) {
            pauta.setStatus(PautaStatusEnum.FINALIZADA);
            pautaRepository.save(pauta);

            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.ERRO_VOTO_PAUTA_JA_ENCERRADA);
        }

        if (votoRepository.existsByIdPautaAndIdAssociado(votoRequest.idPauta(), votoRequest.idAssociado()))
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.ERRO_VOTO_JA_FEITO_PARA_PAUTA_E_ASSOCIADO,
                    votoRequest.idPauta(), votoRequest.idAssociado());

        votoRepository.save(VotoBuilder.gerarEntidade(votoRequest));
    }


    public ContagemVotosDTO contabilizarVotos(final Long idPauta){
        Long sim = votoRepository.countByIdPautaAndVoto(idPauta, OpcaoVotoEnum.SIM);
        Long nao = votoRepository.countByIdPautaAndVoto(idPauta, OpcaoVotoEnum.NAO);
        return new ContagemVotosDTO(sim, nao);
    }
}
