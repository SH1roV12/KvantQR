package ru.yaharuott.servhack.adapter.in.web.auth;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yaharuott.servhack.adapter.in.web.auth.dto.AuthResponse;
import ru.yaharuott.servhack.adapter.in.web.participants.dto.GetByCode;
import ru.yaharuott.servhack.adapter.in.web.participants.dto.ParticipantWithGroupData;
import ru.yaharuott.servhack.application.port.in.ParticipantsService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${app.staff.secret-key}")
    private String staffSecretKey;
    private final ParticipantsService service;
    public  AuthController(ParticipantsService service){
        this.service = service;
    }

    @PostMapping("/login")
    public AuthResponse loginByCode(@Valid @RequestBody GetByCode req) {
        String key = req.passCode() == null ? "" : req.passCode().trim().toUpperCase();

        if (staffSecretKey.equals(key)) {
            return new AuthResponse("STAFF", null);
        }

        ParticipantWithGroupData participant = service.getParticipantByCode(key);
        return new AuthResponse("PARTICIPANT", participant);
    }

}
