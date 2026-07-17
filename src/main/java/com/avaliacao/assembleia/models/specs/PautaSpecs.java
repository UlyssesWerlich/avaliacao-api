package com.avaliacao.assembleia.models.specs;

import com.avaliacao.assembleia.models.entities.Pauta;
import com.avaliacao.assembleia.models.enums.PautaStatusEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@NoArgsConstructor
@AllArgsConstructor
public class PautaSpecs implements Specification<Pauta> {

    private String tema;
    private String descricao;
    private PautaStatusEnum status;

    @Override
    public Predicate toPredicate(@NonNull Root<Pauta> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(tema)){
            predicates.add(cb.like(root.get("tema"), "%" + tema + "%"));
        }
        if (StringUtils.hasText(descricao)){
            predicates.add(cb.like(root.get("descricao"), "%" + descricao + "%"));
        }
        if (nonNull(status)){
            predicates.add(cb.equal(root.get("status"), status.toString()));
        }
        return cb.and(predicates.toArray(new Predicate[0]));

    }

}
