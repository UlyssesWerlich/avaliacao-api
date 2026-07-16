package com.avaliacao.assembleia.models.builders;

import com.avaliacao.assembleia.models.dtos.VotoRequestDTO;
import com.avaliacao.assembleia.models.entities.Voto;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class VotoBuilder {

    public static Voto gerarEntidade(VotoRequestDTO votoRequest){
        Voto voto = new Voto();
        voto.setIdPauta(votoRequest.idPauta());
        voto.setIdAssociado(votoRequest.idAssociado());
        voto.setVoto(votoRequest.voto());
        voto.setDataVoto(LocalDateTime.now());
        return voto;
    }

}
