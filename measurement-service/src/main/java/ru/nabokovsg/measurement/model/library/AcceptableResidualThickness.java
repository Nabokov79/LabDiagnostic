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
@Table(name = "acceptable_residual_thickness")
public class AcceptableResidualThickness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_library_id")
    private Long equipmentLibraryId;
    @Column(name = "element_library_id")
    private Long elementLibraryId;
    @Column(name = "part_element_library_id")
    private Long partElementLibraryId;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "diameter")
    private Integer diameter;
    @Column(name = "thickness")
    private Double thickness;
    @Column(name = "acceptable_thickness")
    private Double acceptableThickness;
    @Column(name = "acceptable_percent")
    private Integer acceptablePercent;
    @Column(name = "measurement_error")
    private Float measurementError;
}