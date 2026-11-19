package ru.yaharuott.servhack.adapter.in.web.participants.dto;

import ru.yaharuott.servhack.adapter.in.web.group.dto.GroupResponse;

    public record ParticipantWithGroupData(
            String fullName,
            String passCode,
            Boolean checkedIn,
            GroupResponse group
    ) {
    }
