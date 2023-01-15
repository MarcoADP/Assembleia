package com.marco.assembleia.tela;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TelaBotaoDTO {

    private String texto;
    private String url;
    private String body;

    public TelaBotaoDTO(String texto, String url) {
        this.texto = texto;
        this.url = url;
    }

    public static TelaBotaoDTO createBotaoOk(String texto, String url, String body) {
        return new TelaBotaoDTO(texto, url, body);
    }

    public static TelaBotaoDTO createBotaoCancelar(String texto, String url) {
        return new TelaBotaoDTO(texto, url);
    }
}
