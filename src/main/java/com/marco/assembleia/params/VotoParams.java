package com.marco.assembleia.params;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class VotoParams {

    @NotNull(message = "A sessão referente ao voto é obrigatória")
    private Long sessaoId;

    @NotNull(message = "O Usuário votante é obrigatório")
    private Integer usuarioId;

    private Boolean voto;

}
