package ru.nabokovsg.laboratoryqc.model;

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
@Table(name = "regulatory_technical_documentations")
public class RegulatoryTechnicalDocumentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "view", nullable = false)
    private String view;
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "title", nullable = false)
    private String title;
}