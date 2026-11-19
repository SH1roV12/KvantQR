package ru.yaharuott.servhack.adapter.in.web.excursion;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.yaharuott.servhack.adapter.in.web.excursion.dto.AddExcursion;
import ru.yaharuott.servhack.adapter.in.web.excursion.dto.ExcursionResponse;
import ru.yaharuott.servhack.adapter.in.web.staff.StaffOnly;
import ru.yaharuott.servhack.application.port.in.ExcursionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/excursions")
public class ExcursionController {
    private final ExcursionService service;
    public ExcursionController(ExcursionService service){
        this.service = service;
    }

    @StaffOnly
    @PostMapping("/")
    public ExcursionResponse addExcursion(@Valid @RequestBody AddExcursion excursion){
        return service.addExcursion(excursion);
    }

    @GetMapping("/")
    public List<ExcursionResponse> getAllExcursions(){
        return service.getExcursions();
    }

}
