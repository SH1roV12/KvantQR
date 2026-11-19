package ru.yaharuott.servhack.application.port.in;

import ru.yaharuott.servhack.adapter.in.web.excursion.dto.AddExcursion;
import ru.yaharuott.servhack.adapter.in.web.excursion.dto.ExcursionResponse;

import java.util.List;

public interface ExcursionService {
    List<ExcursionResponse> getExcursions();

    ExcursionResponse addExcursion(AddExcursion excursion);
}
