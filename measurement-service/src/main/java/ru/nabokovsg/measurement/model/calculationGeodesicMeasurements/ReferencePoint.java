package ru.nabokovsg.measurement.model.calculationGeodesicMeasurements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reference_points")
public class ReferencePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_measurement_location")
    private Integer numberMeasurementLocation;
    @Column(name = "reference_point_value")
    private Integer referencePointValue;
    @Column(name = "deviation")
    private Integer deviation;
    @Column(name = "precipitation")
    private Integer precipitation;
    @Column(name = "acceptable_precipitation")
    private Boolean acceptablePrecipitation;
    @OneToMany(mappedBy = "referencePoint",
               orphanRemoval = true,
               cascade = CascadeType.REMOVE,
               fetch = FetchType.EAGER)
    private List<DeviationYear> deviationYeas;
    @ManyToOne
    @JoinColumn(name = "geodesic_measurement_id")
    @JsonIgnore
    private CalculationGeodeticMeasuring calculationGeodeticPoints;
}