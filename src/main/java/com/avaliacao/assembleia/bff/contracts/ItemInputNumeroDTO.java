package com.avaliacao.assembleia.bff.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInputTextoDTO implements Item {

    private String tipo;
    private String id;
    private String titulo;
    private String valor;
}
