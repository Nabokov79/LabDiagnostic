package ru.nabokovsg.document.model;

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
@Table(name = "document_templates")
public class DocumentTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "work_type")
    @Enumerated(EnumType.STRING)
    private WorkType workType;
    @Column(name = "type")
    private String type;
    @Column(name = "title")
    private String title;
    @Column(name = "equipment_library_id")
    private Long equipmentLibraryId;
    @Column(name = "purpose_work")
    private String purposeWork;
    @Column(name = "appendices")
    private String appendices;
    @Column(name = "location_measurements")
    private String locationMeasurements;
    @Column(name = "measurement_error")
    private String measurementError;
}