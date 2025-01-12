package ru.nabokovsg.measurement.model.calculationGeodesicMeasurements;

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
@Table(name = "control_points")
public class ControlPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_measurement_location")
    private Integer numberMeasurementLocation;
    @Column(name = "control_point_value")
    private Integer controlPointValue;
    @Column(name = "deviation")
    private Integer deviation;
    @ManyToOne
    @JoinColumn(name = "geodesic_measurement_id")
    @JsonIgnore
    private CalculationGeodeticMeasuring calculationGeodeticPoints;
}