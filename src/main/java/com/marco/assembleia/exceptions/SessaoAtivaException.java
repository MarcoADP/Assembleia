package com.marco.assembleia.exceptions;

public class SessaoAtivaException extends RuntimeException {

    public SessaoAtivaException(String pauta) {
        super(String.format("A sessão para sobre a pauta %s ainda está ativa", pauta));
    }

}
