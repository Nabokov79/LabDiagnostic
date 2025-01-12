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
@Table(name = "qcl_document_types")
public class QCLDocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "diagnosis_type")
    private String diagnosisType;
    @Column(name = "type")
    private String type;
    @Column(name = "document_title")
    private String documentTitle;
}