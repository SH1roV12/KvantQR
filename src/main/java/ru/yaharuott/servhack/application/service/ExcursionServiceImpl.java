package ru.yaharuott.servhack.application.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.yaharuott.servhack.adapter.in.web.excursion.dto.AddExcursion;
import ru.yaharuott.servhack.adapter.in.web.excursion.dto.ExcursionResponse;
import ru.yaharuott.servhack.adapter.in.web.group.dto.GroupResponse;
import ru.yaharuott.servhack.adapter.out.persistence.ExcursionRepository;
import ru.yaharuott.servhack.application.port.in.ExcursionService;
import ru.yaharuott.servhack.domain.entity.Excursion;
import ru.yaharuott.servhack.domain.entity.Group;
import ru.yaharuott.servhack.domain.exceptions.CannotFindAnyExcursions;
import ru.yaharuott.servhack.domain.exceptions.CannotFindWithThisPhoneException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcursionServiceImpl implements ExcursionService {

    private final ExcursionRepository repository;
    public ExcursionServiceImpl(ExcursionRepository repository){
        this.repository = repository;
    }

    @Override
    public List<ExcursionResponse> getExcursions() {
        List<Excursion> excursions =  repository.findAll();
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
        Excursion saved = repository.save(excursion1);
        return new ExcursionResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getStartsAt());
    }
}
