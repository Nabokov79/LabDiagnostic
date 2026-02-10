package ru.nabokovsg.referencebooks.model;

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
    @Column(name = "equipment")
    private String equipmentLibrary;
    @Column(name = "equipment_id")
    private Long equipmentLibraryId;
    @Column(name = "diagnosis")
    private String diagnosis;
    @Column(name = "measurements")
    private String measurements;
    @Column(name = "measurements_type")
    private String measurementsType;
}