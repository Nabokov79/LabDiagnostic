package ru.nabokovsg.laboratoryqc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "qcl_certificates")
public class QualityControlLaboratoryCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "document_name")
    private String documentName;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "date_issue")
    private LocalDate dateIssue;
    @Column(name = "validity_period")
    private LocalDate validityPeriod;
    @Column(name = "organization")
    private String organization;
}