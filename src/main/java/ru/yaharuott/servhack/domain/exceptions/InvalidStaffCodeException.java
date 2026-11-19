package ru.yaharuott.servhack.domain.exceptions;

public class InvalidStaffCodeException extends RuntimeException {
    public InvalidStaffCodeException(String message) {
        super(message);
    }
}
