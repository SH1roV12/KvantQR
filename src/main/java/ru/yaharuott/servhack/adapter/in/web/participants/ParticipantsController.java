package ru.yaharuott.servhack.adapter.in.web.participants;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yaharuott.servhack.adapter.in.web.participants.dto.AddParticipant;
import ru.yaharuott.servhack.adapter.in.web.participants.dto.CheckInResponse;
import ru.yaharuott.servhack.adapter.in.web.participants.dto.ParticipantsResponse;
import ru.yaharuott.servhack.adapter.in.web.staff.StaffOnly;
import ru.yaharuott.servhack.application.port.in.ParticipantsService;

import java.util.List;

@RestController
@RequestMapping("api/v1/participants")
public class ParticipantsController {

    private final ParticipantsService service;
    public ParticipantsController(ParticipantsService srv){
        this.service = srv;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{groupId}")
    public ParticipantsResponse addNewParticipant(@PathVariable(name = "groupId") Long groupId, @Valid @RequestBody AddParticipant req){
        return service.addNewParticipant(req.fullName(), groupId);
    }

    @GetMapping("/{groupId}")
    public List<ParticipantsResponse> getParticipants(@PathVariable(name = "groupId") Long groupId){
        return service.getParticipants(groupId);
    }


    @StaffOnly
    @PatchMapping("/checkin/{passCode}")
    public CheckInResponse checkIn(@PathVariable("passCode") String passCode) {
        return service.checkParticipantByPassCode(passCode);
    }
}
