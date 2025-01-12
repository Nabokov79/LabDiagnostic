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
@Table(name = "neighboring_points")
public class CalculationNeighboringPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_place_number")
    private Integer firstPlaceNumber;
    @Column(name = "second_place_number")
    private Integer secondPlaceNumber;
    @Column(name = "deviation")
    private Integer deviation;
    @Column(name = "acceptable_difference")
    private Boolean acceptableDifference;
    @ManyToOne
    @JoinColumn(name = "geodesic_measurement_id")
    @JsonIgnore
    private CalculationGeodeticMeasuring calculationGeodeticPoints;
}