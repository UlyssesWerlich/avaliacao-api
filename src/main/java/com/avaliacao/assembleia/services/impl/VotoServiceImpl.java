package com.avaliacao.assembleia.services.impl;

import com.avaliacao.assembleia.handler.BusinessException;
import com.avaliacao.assembleia.handler.ErrorCodeEnum;
import com.avaliacao.assembleia.integrations.UserInfoClient;
import com.avaliacao.assembleia.models.builders.VotoBuilder;
import com.avaliacao.assembleia.models.dtos.ContagemVotosDTO;
import com.avaliacao.assembleia.models.dtos.VotoRequestDTO;
import com.avaliacao.assembleia.models.dtos.userinfo.UserInfoDTO;
import com.avaliacao.assembleia.models.entities.Pauta;
import com.avaliacao.assembleia.models.enums.OpcaoVotoEnum;
import com.avaliacao.assembleia.models.enums.PautaStatusEnum;
import com.avaliacao.assembleia.repositories.PautaRepository;
import com.avaliacao.assembleia.repositories.VotoRepository;
import com.avaliacao.assembleia.services.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final PautaRepository pautaRepository;
    private final UserInfoClient userInfoClient;


    // UMA OPÇÃO PARA EVITAR GARGALO DE PROCESSAMENTO É IMPLEMENTAR A FUNÇÃO DE FORMA ASSÍNCRONA UTILIZANDO MENSAGERIA
    public void votar(final VotoRequestDTO votoRequest) {

        Pauta pauta = pautaRepository.findByIdAndStatus(votoRequest.idPauta(), PautaStatusEnum.INICIADA)
                .orElseThrow(() -> new BusinessException(BAD_REQUEST, ErrorCodeEnum.ERRO_VOTO_PAUTA_NAO_ENCONTRADA));

        if (pauta.getDataFinalizacaoSessao().isBefore(LocalDateTime.now())) {
            pauta.setStatus(PautaStatusEnum.FINALIZADA);
            pautaRepository.save(pauta);

            throw new BusinessException(BAD_REQUEST, ErrorCodeEnum.ERRO_VOTO_PAUTA_JA_ENCERRADA);
        }

        if (votoRepository.existsByIdPautaAndIdAssociado(votoRequest.idPauta(), votoRequest.idAssociado()))
            throw new BusinessException(BAD_REQUEST, ErrorCodeEnum.ERRO_VOTO_JA_FEITO_PARA_PAUTA_E_ASSOCIADO,
                    votoRequest.idPauta(), votoRequest.idAssociado());

        votoRepository.save(VotoBuilder.gerarEntidade(votoRequest));
    }


    public ContagemVotosDTO contabilizarVotos(final Long idPauta){
        if (!pautaRepository.existsByIdAndStatus(idPauta, PautaStatusEnum.FINALIZADA))
                throw new BusinessException(HttpStatus.NOT_FOUND, ErrorCodeEnum.ERRO_PAUTA_NAO_ENCONTRADA, idPauta);

        Long sim = votoRepository.countByIdPautaAndVoto(idPauta, OpcaoVotoEnum.SIM);
        Long nao = votoRepository.countByIdPautaAndVoto(idPauta, OpcaoVotoEnum.NAO);

        return new ContagemVotosDTO(sim, nao);
    }

    // A URL INFORMADA NÃO ESTAVA FUNCIONADO NO HEROKU, ASSIM NÃO CONSEGUI FAZER O TESTE PRÁTICO DA INTEGRAÇÃO
    // DE QUALQUER FORMA, ADICIONEI A INTEGRAÇÃO
    private boolean cpfValido(String cpf) {
        UserInfoDTO userInfoDTO = userInfoClient.verificarCpf(cpf);
        return switch (userInfoDTO.getStatus()) {
            case "ABLE_TO_VOTE" -> true;
            default -> false;
        };
    }
}
