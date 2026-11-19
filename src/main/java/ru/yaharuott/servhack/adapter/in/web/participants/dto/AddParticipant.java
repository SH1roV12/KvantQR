package ru.yaharuott.servhack.adapter.in.web.participants.dto;

import jakarta.validation.constraints.NotBlank;

public record AddParticipant(
        @NotBlank(message = "Имя не должно быть пустым")
        String fullName
) {
}
