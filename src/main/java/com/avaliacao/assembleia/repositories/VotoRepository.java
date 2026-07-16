package com.avaliacao.assembleia.repositories;

import com.avaliacao.assembleia.models.entities.Voto;
import com.avaliacao.assembleia.models.enums.OpcaoVotoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    Boolean existsByIdPautaAndIdAssociado(Long idPauta, String idAssociado);

    @Query(value = """
        select count(v)
        from Voto v
            where v.idPauta = :idPauta
                and v.voto = :voto
    """)
    Long countByIdPautaAndVoto(Long idPauta, OpcaoVotoEnum voto);
}
