package com.avaliacao.assembleia.bff.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemTextoDTO implements Item {

    private String tipo;
    private String texto;
}
