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
@Table(name = "deviations_geodesy_library")
public class DeviationsGeodesyLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "equipment_library_id")
    private Long equipmentLibraryId;
    @Column(name = "heat_carrier")
    private String heatCarrier;
    @Column(name = "equipment_condition")
    private String equipmentCondition;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "acceptable_precipitation")
    private Integer acceptablePrecipitation;
    @Column(name = "max_difference_neighboring_points")
    private Integer maxDifferenceNeighboringPoints;
    @Column(name = "max_difference_diametric_points")
    private Integer maxDifferenceDiametricPoints;
}