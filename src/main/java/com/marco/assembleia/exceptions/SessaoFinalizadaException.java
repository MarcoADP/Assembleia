package com.marco.assembleia.exceptions;

public class SessaoFinalizadaException extends RuntimeException {

    public SessaoFinalizadaException(String pauta) {
        super(String.format("A sessão para sobre a pauta %s acabou", pauta));
    }

}
