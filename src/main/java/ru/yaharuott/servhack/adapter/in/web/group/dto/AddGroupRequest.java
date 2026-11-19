package ru.yaharuott.servhack.adapter.in.web.group.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AddGroupRequest(
        Long excursionId,
        @NotBlank(message = "Название школы не должно быть пустым")
        String schoolName,
        LocalDate visitDate,
        @NotBlank(message = "Имя не должно быть пустым")
        String contactPhone
) {
}
