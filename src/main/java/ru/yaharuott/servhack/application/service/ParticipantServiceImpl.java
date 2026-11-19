package ru.yaharuott.servhack.application.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.yaharuott.servhack.adapter.in.web.excursion.dto.ExcursionResponse;
import ru.yaharuott.servhack.adapter.in.web.group.dto.GroupResponse;
import ru.yaharuott.servhack.adapter.in.web.participants.dto.CheckInResponse;
import ru.yaharuott.servhack.adapter.in.web.participants.dto.ParticipantWithGroupData;
import ru.yaharuott.servhack.adapter.in.web.participants.dto.ParticipantsResponse;
import ru.yaharuott.servhack.adapter.out.persistence.ExcursionRepository;
import ru.yaharuott.servhack.adapter.out.persistence.GroupRepository;
import ru.yaharuott.servhack.adapter.out.persistence.ParticipantRepository;
import ru.yaharuott.servhack.application.port.in.ParticipantsService;
import ru.yaharuott.servhack.domain.entity.Excursion;
import ru.yaharuott.servhack.domain.entity.Group;
import ru.yaharuott.servhack.domain.entity.Participant;
import ru.yaharuott.servhack.domain.exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantsService {

    private final ParticipantRepository participantRepository ;
    private final GroupRepository groupRepository;
    private final ExcursionRepository excursionRepository;
    public ParticipantServiceImpl(ParticipantRepository participantRepository, GroupRepository groupRepository,ExcursionRepository excursionRepository ){
        this.participantRepository = participantRepository;
        this.groupRepository = groupRepository;
        this.excursionRepository = excursionRepository;
    }

    @Transactional
    @Override
    public ParticipantsResponse addNewParticipant(String fullName, Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(()-> new
                CannotFindGroupByIdException("Группа не найдена"));
        Participant participant = new Participant(group,fullName );
        Participant saved =  participantRepository.save(participant);
        return new ParticipantsResponse(saved.getFullName(), saved.getPassCode(), saved.getCheckedIn());
    }

    @Override
    public List<ParticipantsResponse> getParticipants(Long groupId) {
        List<Participant> participants = participantRepository.findAllByGroupId(groupId);
        if(participants.isEmpty()){
            throw new GroupsEmptyException("Группа пустая");
        }
        List<ParticipantsResponse> finded = new ArrayList<>();
        for (Participant participant: participants){
            finded.add(new ParticipantsResponse(
                    participant.getFullName(),
                    participant.getPassCode(),
                    participant.getCheckedIn()
            ));
        }
        return finded;
    }

    @Override
    @Transactional
    public ParticipantWithGroupData getParticipantByCode(String passCode) {

        Participant participant =  participantRepository.findByPassCode(passCode).orElseThrow(() -> new ParticipantsNotFoundException("Пользователь с данным кодом не найден"));
        Excursion excursion = excursionRepository.findById(participant.getGroup().getExcursionId()).orElseThrow(()-> new CannotFindAnyExcursions("Эксурсии нет с данным id"));
        return new ParticipantWithGroupData(
                participant.getFullName(),
                participant.getPassCode(),
                participant.getCheckedIn(),
                new GroupResponse(
                        participant.getGroup().getId(),
                        participant.getGroup().getSchoolName(),
                        participant.getGroup().getVisitDate(),
                        new ExcursionResponse(
                                excursion.getId(),
                                excursion.getTitle(),
                                excursion.getDescription(),
                                excursion.getStartsAt()
                        )
                )
        );

    }


    @Transactional
    @Override
    public CheckInResponse checkParticipantByPassCode(String passCode) {
        Participant participant =  participantRepository.findByPassCode(passCode).orElseThrow(() -> new ParticipantsNotFoundException("Пользователь с данным кодом не найден"));;
        if (participant.getCheckedIn()){
            throw new ParticipantsAlreadyCheckedInException("Пользователь уже отмечен");
        }
        LocalDate today = LocalDate.now();
        if (!participant.getGroup().getVisitDate().equals(today)){
            throw new GroupVisitDateNotEqualsException("Экскурсия еще не доступна");
        }

        participant.setCheckedIn(true);
        participantRepository.save(participant);
        return new CheckInResponse(
                String.format("Добро пожаловать, %s!", participant.getFullName())
        );
    }
}
