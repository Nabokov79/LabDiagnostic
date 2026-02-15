package ru.nabokovsg.referencebooks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diagnosis_library")
public class DiagnosisLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "diagnosis")
    private String diagnosis;
    @Column(name = "measurements")
    private String measurements;
    @Column(name = "measurements_type")
    private String measurementsType;
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    @JsonIgnore
    private EquipmentLibrary equipment;
}