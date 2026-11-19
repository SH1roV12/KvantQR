package ru.yaharuott.servhack.adapter.in.web.participants.dto;

import jakarta.validation.constraints.NotBlank;

public record GetByCode(
        @NotBlank
        String passCode
) {
}
