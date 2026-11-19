package ru.yaharuott.servhack.application.port.in;

import ru.yaharuott.servhack.adapter.in.web.excursion.dto.ExcursionResponse;
import ru.yaharuott.servhack.adapter.in.web.group.dto.AddGroupRequest;
import ru.yaharuott.servhack.adapter.in.web.group.dto.GroupResponse;
import ru.yaharuott.servhack.adapter.in.web.group.dto.GroupSummaryResponse;

import java.util.List;


public interface GroupService {
    GroupResponse addNewGroup(AddGroupRequest request);
    List<GroupResponse> getGroupByPhoneNumber(String phone);
    List<GroupSummaryResponse> getGroupsSummary();
}
