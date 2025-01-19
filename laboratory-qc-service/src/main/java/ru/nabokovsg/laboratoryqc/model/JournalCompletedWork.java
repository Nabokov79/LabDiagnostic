package ru.nabokovsg.laboratoryqc.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "journal_completed_works")
public class JournalCompletedWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "journal_type")
    private String journalType;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "branch_id")
    private Long branchId;
    @Column(name = "heat_supply_area_id")
    private Long heatSupplyAreaId;
    @Column(name = "exploitation_region_id")
    private Long exploitationRegionId;
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "branch")
    private String branch;
    @Column(name = "heat_supply_area")
    private String heatSupplyArea;
    @Column(name = "exploitation_region")
    private String exploitationRegion;
    @Column(name = "address")
    private String address;
    @Column(name="equipment_id")
    private Long equipmentId;
    @Column(name="element_id")
    private Long elementId;
    @Column(name = "equipment_name")
    private String equipmentName;
    @Column(name = "document")
    private String document;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id",  nullable = false)
    private QCLDocumentType documentType;
    @Column(name = "task_source")
    private String taskSource;
    @Column(name = "measuring_instruments")
    private String measuringInstruments;
    @Column(name = "comment")
    private String comment;
    @Column(name = "chief")
    private String chief;
    @Column(name = "employees")
    private String employees;
    @Column(name = "methodological_document")
    private String methodologicalDocument;
    @Column(name = "regulatory_document")
    private String regulatoryDocument;
    @Column(name = "document_number")
    private Integer documentNumber;
    @Column(name = "next_date")
    private LocalDate nextDate;
    @Column(name = "status")
    private String status;
    @Column(name = "appendices")
    private String appendices;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "chief_id",  nullable = false)
    private QCLEmployee headWorkPerformer;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "journal_completed_work_employees",
            joinColumns =  {@JoinColumn(name = "journal_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    @ToString.Exclude
    private Set<QCLEmployee> workPerformers;
}