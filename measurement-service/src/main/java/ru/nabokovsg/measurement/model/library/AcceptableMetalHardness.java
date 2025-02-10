package ru.nabokovsg.measurement.model.library;

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
@Table(name = "acceptable_metal_hardness")
public class AcceptableMetalHardness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_library_id")
    private Long equipmentLibraryId;
    @Column(name = "element_library_id")
    private Long elementLibraryId;
    @Column(name = "part_element_library_id")
    private Long partElementLibraryId;
    @Column(name = "standard_size_string")
    private String standardSize;
    @Column(name = "thickness")
    private Double thickness;
    @Column(name = "min_diameter")
    private Integer minAcceptableDiameter;
    @Column(name = "min_thickness")
    private Double minAcceptableThickness;
    @Column(name = "min_hardness")
    private Integer minAcceptableHardness;
    @Column(name = "max_hardness")
    private Integer maxAcceptableHardness;
    @Column(name = "measurement_error")
    private Float measurementError;
}