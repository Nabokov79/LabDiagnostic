package ru.nabokovsg.referencebooks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "name")
    private String name;
    @Column(name = "measured_parameters")
    private String measuredParameters;
    @Column(name = "thickness")
    private String thickness;
    @Column(name = "total_length")
    private String totalLength;
    @Column(name = "assessment_area")
    private String assessmentArea;
    @Column(name = "defects_quantity")
    private Integer defectsQuantity;
    @Column(name = "assessment")
    private String qualityAssessment;
    @Column(name = "quality_assessment")
    @Enumerated(EnumType.STRING)
    private QualityAssessment qualityAssessmentType;
    @Column(name = "without_naming_parameter")
    private Boolean withoutNamingParameter;
    @Column(name = "assessment_area_mm")
    private Double assessmentAreaMM;
    @Column(name = "assessment_area_percentage")
    private Double assessmentAreaPercentage;
    @Column(name = "total_length_mm")
    private Double totalLengthMM;
    @Column(name = "total_length_percentage")
    private Double totalLengthPercentage;
    @Column(name = "min_diameter")
    private Float minDiameter;
    @Column(name = "max_diameter")
    private Float maxDiameter;
    @Column(name = "min_thickness")
    private Float minThickness;
    @Column(name = "max_thickness")
    private Float maxThickness;
    @OneToMany(mappedBy = "defect",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private List<MeasurementParameterLibrary> measuredParametersLibrary;
    @ManyToOne
    @JoinColumn(name = "documentation_id")
    @JsonIgnore
    private RegulatoryDocumentationLibrary documentation;
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    @JsonIgnore
    private EquipmentLibrary equipment;
}