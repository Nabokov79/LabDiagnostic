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
    private long id;
    @Column(name = "diagnosis")
    private String diagnosis;
    @Column(name = "document")
    private String document;
    @Column(name = "title")
    private String title;
}