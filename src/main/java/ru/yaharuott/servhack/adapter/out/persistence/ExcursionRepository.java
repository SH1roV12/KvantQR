package ru.yaharuott.servhack.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yaharuott.servhack.domain.entity.Excursion;

import java.util.List;
import java.util.Optional;

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {

}
