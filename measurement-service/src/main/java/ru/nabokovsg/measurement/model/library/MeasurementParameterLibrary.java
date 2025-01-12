package ru.nabokovsg.measurement.model.library;

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
@Table(name = "library_measurement_parameters")
public class MeasurementParameterLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "parameter_name")
    private String parameterName;
    @Column(name = "unit_measurement")
    private String unitMeasurement;
    @ManyToOne
    @JoinColumn(name = "defect_id")
    @JsonIgnore
    private DefectLibrary defect;
    @ManyToOne
    @JoinColumn(name = "repair_id")
    @JsonIgnore
    private RepairLibrary elementRepair;
}