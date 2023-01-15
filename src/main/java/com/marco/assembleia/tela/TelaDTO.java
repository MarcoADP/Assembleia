package com.marco.assembleia.tela;


import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TelaDTO {

    private TelaTipo tipo;
    private String titulo;
    private List<TelaItemDTO> itens = new ArrayList<>();
    private TelaBotaoDTO botaoOk;
    private TelaBotaoDTO botaoCancelar;

    public TelaDTO(TelaTipo tipo, String titulo, List<TelaItemDTO> itens) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.itens = itens;
    }

    public static TelaDTO createFormulario(
            String titulo,
            List<TelaItemDTO> itens,
            TelaBotaoDTO botaoOk,
            TelaBotaoDTO botaoCancelar
    ) {
        return new TelaDTO(TelaTipo.FORMULARIO, titulo, itens, botaoOk, botaoCancelar);
    }

    public static TelaDTO createSelecao(String titulo, List<TelaItemDTO> itens) {
        return new TelaDTO(TelaTipo.SELECAO, titulo, itens);
    }

}
