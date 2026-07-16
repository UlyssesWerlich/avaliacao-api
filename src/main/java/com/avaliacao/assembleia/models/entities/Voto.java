package com.avaliacao.assembleia.models.entities;

import com.avaliacao.assembleia.models.enums.OpcaoVotoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "voto")
public class Voto {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idPauta;

    @Column
    private String idAssociado;

    @Column
    @Enumerated(EnumType.STRING)
    private OpcaoVotoEnum voto;

    @Column
    private LocalDateTime dataVoto;
}
