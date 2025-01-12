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
@Table(name = "qcl_employee_certificates")
public class QCLEmployeeCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "document_name")
    private String documentName;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "control_name")
    private String controlName;
    @Column(name = "level")
    private Integer level;
    @Column(name = "date_issue")
    private LocalDate dateIssue;
    @Column(name = "validity_period")
    private LocalDate validityPeriod;
    @Column(name = "points")
    private String points;
    @Column(name = "organization")
    private String organization;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",  nullable = false)
    private QCLEmployee employee;
}