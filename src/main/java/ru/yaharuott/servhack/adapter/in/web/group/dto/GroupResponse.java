package ru.yaharuott.servhack.adapter.in.web.group.dto;

import ru.yaharuott.servhack.adapter.in.web.excursion.dto.ExcursionResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record GroupResponse(
        Long id,
        String schoolName,
        LocalDate visitDate,
        ExcursionResponse excursionResponse
){}
