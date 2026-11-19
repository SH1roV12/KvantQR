package ru.yaharuott.servhack.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yaharuott.servhack.domain.entity.Group;
import ru.yaharuott.servhack.domain.entity.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findAllByGroupId(Long groupId);
    Optional<Participant> findByPassCode(String passCode);
}
