package com.marco.assembleia.exceptions;

public class PautaComSessaoAtivaException extends RuntimeException {

    public PautaComSessaoAtivaException(String pauta) {
        super(String.format("A pauta com assunto %s já possui uma sessão ativa", pauta));
    }

}
