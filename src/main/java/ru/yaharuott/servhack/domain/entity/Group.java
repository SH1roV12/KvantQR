package ru.yaharuott.servhack.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "excursion_groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_name", nullable = false)
    @NotBlank
    private String schoolName;

    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;

    @Column(name = "contact_phone", nullable = false)
    @NotBlank
    @Size(min = 10)
    private String contactPhone;

    @Column(name = "excursion_id", nullable = false)
    private Long excursionId;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();

    protected Group() {
    }

    public Group(String schoolName, LocalDate visitDate, String contactPhone, Long excursionId) {
        this.schoolName = schoolName;
        this.visitDate = visitDate;
        this.contactPhone = contactPhone;
        this.excursionId = excursionId;
    }
}
