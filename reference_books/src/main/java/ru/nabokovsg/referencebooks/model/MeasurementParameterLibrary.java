package ru.nabokovsg.referencebooks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "measurement_parameters_library")
public class MeasurementParameterLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "unit_measurement")
    private String unitMeasurement;
    @Column(name = "calculation_type")
    @Enumerated(EnumType.STRING)
    private ParameterCalculationType calculationType;
    @Column(name = "calculation")
    private String calculation;
    @Column(name = "acceptable_min_value")
    private Float acceptableMinValue;
    @Column(name = "acceptable_max_value")
    private Float acceptableMaxValue;
    @Column(name = "calculate_residual_thickness")
    private Boolean calculateByResidualThickness;

    @ManyToOne
    @JoinColumn(name = "defect_id")
    @JsonIgnore
    private DefectLibrary defect;
    @ManyToOne
    @JoinColumn(name = "repair_id")
    @JsonIgnore
    private RepairLibrary repair;
}