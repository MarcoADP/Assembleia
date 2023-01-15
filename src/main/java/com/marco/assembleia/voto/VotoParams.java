package com.marco.assembleia.voto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VotoParams {

    @NotNull(message = "O Usuário votante é obrigatório")
    private Integer usuarioId;

    private Boolean voto;

}
