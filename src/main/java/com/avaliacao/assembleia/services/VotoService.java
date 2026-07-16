package com.avaliacao.assembleia.services;

import com.avaliacao.assembleia.models.dtos.ContagemVotosDTO;
import com.avaliacao.assembleia.models.dtos.VotoRequestDTO;

public interface VotoService {

    void votar(final VotoRequestDTO votoRequest);

    ContagemVotosDTO contabilizarVotos(final Long idPauta);

}
