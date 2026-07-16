package com.avaliacao.assembleia.repositories;

import com.avaliacao.assembleia.models.entities.Pauta;
import com.avaliacao.assembleia.models.enums.PautaStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    Optional<Pauta> findByIdAndStatus(Long id, PautaStatusEnum status);
}
