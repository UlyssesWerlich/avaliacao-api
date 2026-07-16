package com.avaliacao.assembleia.models.entities;

import com.avaliacao.assembleia.models.enums.PautaStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pautas")
public class Pauta {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tema;

    @Column
    private String descricao;

    @Column
    @Enumerated(EnumType.STRING)
    private PautaStatusEnum status;

    @Column
    private LocalDateTime dataCriacao;

    @Column
    private LocalDateTime dataAberturaSessao;

    @Column
    private LocalDateTime dataFinalizacaoSessao;
}
