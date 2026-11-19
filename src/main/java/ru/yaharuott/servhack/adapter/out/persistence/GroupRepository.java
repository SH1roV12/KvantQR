package ru.yaharuott.servhack.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yaharuott.servhack.adapter.in.web.group.dto.GroupSummaryResponse;
import ru.yaharuott.servhack.domain.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByContactPhone(String contactPhone);
    @Query("""
        SELECT new ru.yaharuott.servhack.adapter.in.web.group.dto.GroupSummaryResponse(
            g.id,
            g.schoolName,
            g.visitDate,
            g.contactPhone,
            COUNT(p.id),
            COUNT(CASE WHEN p.checkedIn = true THEN 1 ELSE NULL END)
        )
        FROM Group g
        LEFT JOIN g.participants p
        GROUP BY g.id, g.schoolName, g.visitDate, g.contactPhone
        ORDER BY g.visitDate ASC
    """)

    List<Group> findAllByExcursionId(Long excursionId);
    List<GroupSummaryResponse> findAllGroupsSummary();
}
