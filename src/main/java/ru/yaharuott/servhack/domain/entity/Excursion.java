package ru.yaharuott.servhack.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "excursions")
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "starts_at", nullable = false)
    private LocalDate startsAt;
    protected Excursion() {
    }
    public Excursion(String title, String description, LocalDate startsAt) {
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
    }
}
