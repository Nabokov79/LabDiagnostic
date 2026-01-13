package ru.nabokovsg.referencebooks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "defects_library")
public class DefectLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_id")
    private Long equipmentLibraryId;
    @Column(name = "name")
    private String name;
    @Column(name = "quality_assessment")
    @Enumerated(EnumType.STRING)
    private QualityAssessment qualityAssessmentType;
    @Column(name = "assessment")
    private String qualityAssessment;
    @Column(name = "without_naming_parameter")
    private Boolean withoutNamingParameter;
    @Column(name = "documentation_id")
    private Long documentationLibraryId;
    @Column(name = "documentation")
    private String documentationLibrary;
    @Column(name = "measurement_parameters")
    private String measurementParameters;
    @Column(name = "assessment_area_mm")
    private Double assessmentAreaMM;
    @Column(name = "assessment_area_percentage")
    private Double assessmentAreaPercentage;
    @Column(name = "defects_quantity")
    private Integer defectsQuantity;
    @Column(name = "total_length_mm")
    private Double totalLengthMM;
    @Column(name = "total_length_percentage")
    private Double totalLengthPercentage;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "min_thickness")
    private Float minThickness;
    @Column(name = "max_thickness")
    private Float maxThickness;
    @OneToMany(mappedBy = "defect",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private List<MeasurementParameterLibrary> measuredParameters;
}