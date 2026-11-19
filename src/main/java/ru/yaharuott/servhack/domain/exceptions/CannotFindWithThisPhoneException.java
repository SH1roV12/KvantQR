package ru.yaharuott.servhack.domain.exceptions;

public class CannotFindWithThisPhoneException extends RuntimeException {
    public CannotFindWithThisPhoneException(String message) {
        super(message);
    }
}
