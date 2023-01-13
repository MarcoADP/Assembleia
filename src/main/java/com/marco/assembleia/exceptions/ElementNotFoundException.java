package com.marco.assembleia.exceptions;

public class ElementNotFoundException extends RuntimeException {

    public ElementNotFoundException(String tipo) {
        super(String.format("O objeto %s não foi encontrado", tipo));
    }

}
