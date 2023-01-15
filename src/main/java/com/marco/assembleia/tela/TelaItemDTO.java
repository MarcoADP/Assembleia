package com.marco.assembleia.tela;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TelaItemDTO {

    private TelaItemTipo tipo;

    private String texto;

    private String id;
    private String titulo;
    private String valor;

    private String url;
    private String body;

    public TelaItemDTO(TelaItemTipo tipo, String texto) {
        this.tipo = tipo;
        this.texto = texto;
    }

    public TelaItemDTO(TelaItemTipo tipo, String id, String titulo, String valor) {
        this.tipo = tipo;
        this.id = id;
        this.titulo = titulo;
        this.valor = valor;
    }

    public TelaItemDTO(String texto, String url, String body) {
        this.texto = texto;
        this.url = url;
        this.body = body;
    }

    public static TelaItemDTO createTexto(String texto) {
        return new TelaItemDTO(TelaItemTipo.TEXTO, texto);
    }

    public static TelaItemDTO createInput(TelaItemTipo tipo, String id, String titulo, String valor) {
        return new TelaItemDTO(tipo, id, titulo, valor);
    }

    public static TelaItemDTO createSelecao(String texto, String url, String body) {
        return new TelaItemDTO(texto, url, body);
    }

    public static TelaItemDTO createSelecao(String texto, String url) {
        return new TelaItemDTO(texto, url, null);
    }
}
