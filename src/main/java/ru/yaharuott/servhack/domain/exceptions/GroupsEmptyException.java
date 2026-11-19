package ru.yaharuott.servhack.domain.exceptions;

public class GroupsEmptyException extends RuntimeException {
    public GroupsEmptyException(String message) {
        super(message);
    }
}
