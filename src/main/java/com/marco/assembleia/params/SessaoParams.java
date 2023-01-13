package com.marco.assembleia.params;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SessaoParams {

    @NotNull(message = "A Pauta da Sessão é obrigatório")
    private Long pautaId;

    private Integer duracao;

}
