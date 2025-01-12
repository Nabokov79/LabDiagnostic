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
@Table(name = "hardness_measurements")
public class HardnessMeasurement {

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
    @Column(name = "measurement_number")
    private Integer measurementNumber;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "measurement_value")
    private Integer measurementValue;
    @Column(name = "status")
    private String status;
    @Column(name = "measurement_status")
    private String measurementStatus;
    @Column(name = "acceptable")
    private Boolean acceptable;
    @Column(name = "invalid")
    private Boolean invalid;
    @Column(name = "no_standard")
    private Boolean noStandard;
}