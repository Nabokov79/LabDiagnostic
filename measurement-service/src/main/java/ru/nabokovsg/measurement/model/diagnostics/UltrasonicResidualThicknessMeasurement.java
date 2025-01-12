package ru.nabokovsg.measurement.model.diagnostics;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ultrasonic_measurements")
public class UltrasonicResidualThicknessMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "measurement_date")
    private LocalDate measurementDate;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "element_id")
    private Long elementId;
    @Column(name = "part_element_id")
    private Long partElementId;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "measurement_number")
    private Integer measurementNumber;
    @Column(name = "min_measurement_value")
    private Double minMeasurementValue;
    @Column(name = "max_measurement_value")
    private Double maxMeasurementValue;
    @Column(name = "max_corrosion")
    private Double maxCorrosion;
    @Column(name = "residual_thickness")
    private Double residualThickness;
    @Column(name = "min_acceptable_value")
    private Double minAcceptableValue;
    @Column(name = "measurement_status")
    private String measurementStatus;
    @Column(name = "status")
    private String status;
}