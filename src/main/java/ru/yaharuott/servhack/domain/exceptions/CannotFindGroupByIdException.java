package ru.yaharuott.servhack.domain.exceptions;

public class CannotFindGroupByIdException extends RuntimeException {
    public CannotFindGroupByIdException(String message) {
        super(message);
    }
}
