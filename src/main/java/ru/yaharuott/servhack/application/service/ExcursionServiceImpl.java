package ru.yaharuott.servhack.application.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.yaharuott.servhack.adapter.in.web.excursion.dto.AddExcursion;
import ru.yaharuott.servhack.adapter.in.web.excursion.dto.ExcursionResponse;
import ru.yaharuott.servhack.adapter.in.web.group.dto.GroupResponse;
import ru.yaharuott.servhack.adapter.out.persistence.ExcursionRepository;
import ru.yaharuott.servhack.adapter.out.persistence.GroupRepository;
import ru.yaharuott.servhack.adapter.out.persistence.ParticipantRepository;
import ru.yaharuott.servhack.application.port.in.ExcursionService;
import ru.yaharuott.servhack.domain.entity.Excursion;
import ru.yaharuott.servhack.domain.entity.Group;
import ru.yaharuott.servhack.domain.exceptions.CannotFindAnyExcursions;
import ru.yaharuott.servhack.domain.exceptions.CannotFindWithThisPhoneException;
import ru.yaharuott.servhack.domain.exceptions.ExcursionHasGroupsException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcursionServiceImpl implements ExcursionService {

    private final GroupRepository groupRepository;
    private final ExcursionRepository excursionRepository;
    public ExcursionServiceImpl( GroupRepository groupRepository,ExcursionRepository excursionRepository ){

        this.groupRepository = groupRepository;
        this.excursionRepository = excursionRepository;
    }

    @Override
    public List<ExcursionResponse> getExcursions() {
        List<Excursion> excursions =  excursionRepository.findAll();
        if (excursions.isEmpty()){
            throw new CannotFindAnyExcursions("Нет доступных экскурсий");
        }
        List<ExcursionResponse> finded = new ArrayList<>();
        for (Excursion excursion: excursions){
            finded.add(new ExcursionResponse(
                    excursion.getId(),
                    excursion.getTitle(),
                    excursion.getDescription(),
                    excursion.getStartsAt()
            ));
        }
        return finded;
    }


    @Transactional
    @Override
    public ExcursionResponse addExcursion(AddExcursion excursion) {
        Excursion excursion1 = new Excursion(
                excursion.title(),
                excursion.description(),
                excursion.startsAt()
        );
        Excursion saved = excursionRepository.save(excursion1);
        return new ExcursionResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getStartsAt());
    }

    @Override
    @Transactional
    public void deleteExcursion(Long id) {
        Excursion excursion = excursionRepository.findById(id)
                .orElseThrow(() -> new CannotFindAnyExcursions("Экскурсия с id " + id + " не найдена"));

        List<Group> groups = groupRepository.findAllByExcursionId(id);
        boolean isPast = excursion.getStartsAt().isBefore(LocalDate.now());

        if (!groups.isEmpty() && !isPast) {
            throw new ExcursionHasGroupsException("Нельзя удалить предстоящую экскурсию, на которую уже записаны группы");
        }
        if (!groups.isEmpty()) {
            groupRepository.deleteAll(groups);
        }

        excursionRepository.delete(excursion);
    }
}
