package ru.yaharuott.servhack.application.port.in;

import ru.yaharuott.servhack.adapter.in.web.participants.dto.AddParticipant;
import ru.yaharuott.servhack.adapter.in.web.participants.dto.CheckInResponse;
import ru.yaharuott.servhack.adapter.in.web.participants.dto.ParticipantWithGroupData;
import ru.yaharuott.servhack.adapter.in.web.participants.dto.ParticipantsResponse;

import java.util.List;

public interface ParticipantsService {
    ParticipantsResponse addNewParticipant(String fullName, Long  groupId);
    List<ParticipantsResponse> getParticipants(Long  groupId);
    ParticipantWithGroupData getParticipantByCode(String passCode);
    CheckInResponse checkParticipantByPassCode(String passCode);
}
