package ru.nabokovsg.measurement.model.calculationGeodesicMeasurements;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "calculation_geodetic_measurings")
public class CalculationGeodeticMeasuring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @OneToMany(mappedBy = "calculationGeodeticPoints")
    private Set<ReferencePoint> referencePoints;
    @OneToMany(mappedBy = "calculationGeodeticPoints")
    private Set<ControlPoint> controlPoints;
    @OneToMany(mappedBy = "calculationGeodeticPoints")
    private Set<CalculationNeighboringPoint> calculationNeighboringPoint;
    @OneToMany(mappedBy = "calculationGeodeticPoints")
    private Set<CalculatingOppositePoint> calculatingOppositePoint;
}