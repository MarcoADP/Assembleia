package com.marco.assembleia.pauta;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PautaParams {

    @NotBlank(message = "O Assunto da Pauta é obrigatório")
    private String assunto;

}
