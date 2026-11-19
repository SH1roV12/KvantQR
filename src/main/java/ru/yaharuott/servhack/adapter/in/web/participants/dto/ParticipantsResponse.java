package ru.yaharuott.servhack.adapter.in.web.participants.dto;

public record ParticipantsResponse(
        String fullName,
        String passCode,
        Boolean checkedIn
) {
}
