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
@Table(name = "residual_thickness_library")
public class ResidualThicknessLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "documentation_id")
    private Long documentationLibraryId;
    @Column(name = "equipment_library_id")
    private Long equipmentLibraryId;
    @Column(name = "element_library_id")
    private Long elementLibraryId;
    @Column(name = "part_element_library_id")
    private Long partElementLibraryId;
    @Column(name = "documentation")
    private String documentationLibrary;
    @Column(name = "equipment_full_name")
    private String equipmentFullName;
    @Column(name = "element_full_name")
    private String elementFullName;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "diameter")
    private Double diameter;
    @Column(name = "thickness")
    private Double thickness;
    @Column(name = "acceptable_thickness_mm")
    private Double  minAcceptableThicknessMM;
    @Column(name = "acceptable_thickness_percent")
    private Integer minAcceptableThicknessPercent;
    @Column(name = "acceptable_thinning_mm")
    private Double  maxAcceptableThinningMM;
    @Column(name = "acceptable_thinning_percent")
    private Integer maxAcceptableThinningPercent;
    @Column(name = "measurement_error")
    private Float measurementError;
}