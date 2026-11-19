package ru.yaharuott.servhack.adapter.in.web.group.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record GroupSummaryResponse(
        Long id,
        String schoolName,
        LocalDate visitDate,
        String contactPhone,
        Long totalParticipants,
        Long checkedInParticipants
) {
}
