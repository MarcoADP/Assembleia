package com.marco.assembleia.exceptions;

public class PautaComSessaoException extends RuntimeException {

    public PautaComSessaoException(String pauta) {
        super(String.format("A pauta com assunto %s já possui uma sessão de votação", pauta));
    }

}
