package ru.yaharuott.servhack.domain.exceptions;

public class ParticipantsAlreadyCheckedInException extends RuntimeException {
    public ParticipantsAlreadyCheckedInException(String message) {
        super(message);
    }
}
