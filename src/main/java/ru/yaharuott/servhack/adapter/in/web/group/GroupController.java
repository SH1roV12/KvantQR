package ru.yaharuott.servhack.adapter.in.web.group;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yaharuott.servhack.adapter.in.web.group.dto.AddGroupRequest;
import ru.yaharuott.servhack.adapter.in.web.group.dto.GroupResponse;
import ru.yaharuott.servhack.adapter.in.web.group.dto.GroupSummaryResponse;
import ru.yaharuott.servhack.adapter.in.web.staff.StaffOnly;
import ru.yaharuott.servhack.application.port.in.GroupService;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final GroupService service;
    public GroupController(GroupService srv){
        this.service = srv;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public GroupResponse addNewGroup( @Valid @RequestBody AddGroupRequest req){
        return service.addNewGroup(req);
    }

    @GetMapping("/")
    public List<GroupResponse> getGroupsByPhone(@RequestParam String phone){
         return service.getGroupByPhoneNumber(phone);
    }




    @StaffOnly
    @GetMapping("/summary")
    public List<GroupSummaryResponse> getSummary(){
        return service.getGroupsSummary();
    }

}
