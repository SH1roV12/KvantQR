package ru.yaharuott.servhack.adapter.in.web.auth.dto;

import ru.yaharuott.servhack.adapter.in.web.participants.dto.ParticipantWithGroupData;

public record AuthResponse(
        String role,
        ParticipantWithGroupData participant
) {
}
