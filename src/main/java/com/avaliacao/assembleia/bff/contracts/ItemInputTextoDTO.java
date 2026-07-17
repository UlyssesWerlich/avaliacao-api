package com.avaliacao.assembleia.bff.contracts;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemInputTextoDTO implements Item {

    private String tipo;
    private String id;
    private String titulo;
    private String valor;
}
