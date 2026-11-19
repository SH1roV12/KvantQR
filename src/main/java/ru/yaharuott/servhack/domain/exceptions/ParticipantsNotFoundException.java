package ru.yaharuott.servhack.domain.exceptions;

public class ParticipantsNotFoundException extends RuntimeException {
    public ParticipantsNotFoundException(String message) {
        super(message);
    }
}
