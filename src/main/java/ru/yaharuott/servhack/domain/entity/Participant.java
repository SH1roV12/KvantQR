package ru.yaharuott.servhack.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "participants")
public class Participant {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    @JsonIgnore
    private Group group;
    @Column(name = "full_name")
    @NotBlank
    private  String fullName;
    @NotBlank

    @Column(name = "pass_code", unique = true)
    private String passCode;

    @Column(name = "checked_in")
    private Boolean checkedIn;

    protected Participant(){}

    public Participant(Group group, String fullName){
        this.group = group;
        this.fullName = fullName;
        this.passCode = "TEMP-"+ UUID.randomUUID().toString();
        this.checkedIn = false;
    }

    @PostPersist
    private void generatePassCode(){
        this.passCode = String.format("KV-2026-%04d", this.id);
    }
}
