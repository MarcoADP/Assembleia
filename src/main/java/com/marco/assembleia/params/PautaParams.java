package com.marco.assembleia.params;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PautaParams {

    @NotBlank(message = "O Assunto da Pauta é obrigatório")
    private String assunto;

}
