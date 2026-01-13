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
@Table(name = "metal_hardness_library")
public class MetalHardnessLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_library_id")
    private Long equipmentLibraryId;
    @Column(name = "element_name")
    private String elementName;
    @Column(name = "element_library_id")
    private Long elementLibraryId;
    @Column(name = "part_element_library_id")
    private Long partElementLibraryId;
    @Column(name = "acceptable_diameter")
    private Integer minAcceptableDiameter;
    @Column(name = "acceptable_thickness")
    private Double minAcceptableThickness;
    @Column(name = "min_hardness")
    private Integer minAcceptableHardness;
    @Column(name = "max_hardness")
    private Integer maxAcceptableHardness;
    @Column(name = "measurement_error")
    private Float measurementError;
}