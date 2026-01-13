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
    @Column(name = "equipment_library_id")
    private Long equipmentLibraryId;
    @Column(name = "element_library_id")
    private Long elementLibraryId;
    @Column(name = "part_element_library_id")
    private Long partElementLibraryId;
    @Column(name = "element_name")
    private String elementName;
    @Column(name = "standard_size")
    private Double standardSize;
    @Column(name = "acceptable_thickness")
    private Double acceptableThickness;
    @Column(name = "acceptable_percent")
    private Integer acceptablePercent;
    @Column(name = "measurement_error")
    private Float measurementError;
}