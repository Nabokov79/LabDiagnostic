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
@Table(name = "geodesy_library")
public class DeviationsGeodesyLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "with_heat_carrier")
    private Boolean withHeatCarrier;
    @Column(name = "heat_carrier")
    private String heatCarrier;
    @Column(name = "condition")
    private Boolean condition;
    @Column(name = "equipment_condition")
    private String equipmentCondition;
    @Column(name = "acceptable_precipitation")
    private Integer acceptablePrecipitation;
    @Column(name = "max_difference_neighboring_points")
    private Integer maxDifferenceNeighboringPoints;
    @Column(name = "max_difference_diametric_points")
    private Integer maxDifferenceDiametricPoints;
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    @JsonIgnore
    private EquipmentLibrary equipment;
    @ManyToOne
    @JoinColumn(name = "documentation_id")
    @JsonIgnore
    private RegulatoryDocumentationLibrary documentation;
}