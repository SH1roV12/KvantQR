package ru.yaharuott.servhack.application.service;


import org.springframework.stereotype.Service;
import ru.yaharuott.servhack.adapter.in.web.excursion.dto.ExcursionResponse;
import ru.yaharuott.servhack.adapter.in.web.group.dto.AddGroupRequest;
import ru.yaharuott.servhack.adapter.in.web.group.dto.GroupResponse;
import ru.yaharuott.servhack.adapter.in.web.group.dto.GroupSummaryResponse;
import ru.yaharuott.servhack.adapter.out.persistence.ExcursionRepository;
import ru.yaharuott.servhack.adapter.out.persistence.GroupRepository;
import ru.yaharuott.servhack.application.port.in.GroupService;
import ru.yaharuott.servhack.domain.entity.Excursion;
import ru.yaharuott.servhack.domain.entity.Group;
import ru.yaharuott.servhack.domain.exceptions.CannotFindAnyExcursions;
import ru.yaharuott.servhack.domain.exceptions.CannotFindWithThisPhoneException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository ;
    private final ExcursionRepository excursionRepository ;
    public GroupServiceImpl(GroupRepository groupRepository, ExcursionRepository excursionRepository){
        this.groupRepository = groupRepository;
        this.excursionRepository = excursionRepository;
    }

    @Override
    public GroupResponse addNewGroup(AddGroupRequest request) {
        Excursion excursion = excursionRepository.findById(request.excursionId()).orElseThrow(
                ()-> new CannotFindAnyExcursions("Эксурсии нет с данным id")
        );
        Group group = new Group(request.schoolName(),request.visitDate(),request.contactPhone(), request.excursionId());
        Group saved = groupRepository.save(group);

        return new GroupResponse(saved.getId(), saved.getSchoolName(), saved.getVisitDate(), new ExcursionResponse(excursion.getId(),excursion.getTitle(),excursion.getDescription(),excursion.getStartsAt()));
    }

    @Override
    public List<GroupResponse> getGroupByPhoneNumber(String phone) {
        List<Group> groups = groupRepository.findByContactPhone(phone);
        if (groups.isEmpty()){
            throw new CannotFindWithThisPhoneException("Группы по номеру телефона не найдено");
        }
        List<GroupResponse> finded = new ArrayList<>();
        for (Group group: groups){
            Excursion findedExcursion = excursionRepository.findById(group.getExcursionId()).orElseThrow(()-> new CannotFindAnyExcursions("Эксурсии нет с данным id"));
            ExcursionResponse excursionResponse = new ExcursionResponse(
                    findedExcursion.getId(),
                    findedExcursion.getTitle(),
                    findedExcursion.getDescription(),
                    findedExcursion.getStartsAt()
            );

            finded.add(new GroupResponse(
                    group.getId(),
                    group.getSchoolName(),
                    group.getVisitDate(),
                    excursionResponse
            ));
        }
        return finded;
    }



    @Override
    public List<GroupSummaryResponse> getGroupsSummary() {
        return groupRepository.findAllGroupsSummary();
    }



}
