package ru.yaharuott.servhack.adapter.in.web.excursion.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ExcursionResponse(
        Long id,
        String title,
        String description,
        LocalDate startsAt
) {
}
