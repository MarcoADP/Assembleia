package com.marco.assembleia.exceptions;

public class UsuarioVotouException extends RuntimeException {

    public UsuarioVotouException(Integer usuarioId) {
        super(String.format("O usuário com id %d já votou", usuarioId));
    }

}
