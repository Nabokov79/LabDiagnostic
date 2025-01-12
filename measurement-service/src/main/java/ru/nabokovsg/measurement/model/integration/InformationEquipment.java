package ru.nabokovsg.measurement.model.integration;

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
@Table(name = "information_equipments")
public class InformationEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="equipment_library_id")
    private Long equipmentLibraryId;
    @Column(name = "element_library_id")
    private Long elementLibraryId;
    @Column(name = "part_element_library_id")
    private Long partElementLibraryId;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "thickness")
    private Double thickness;
    @Column(name = "min_diameter")
    private Integer minDiameter;
    @Column(name = "min_thickness")
    private Double minThickness;
    @Column(name = "max_diameter")
    private Integer maxDiameter;
    @Column(name = "max_thickness")
    private Double maxThickness;
    @Column(name = "heat_carrier")
    private boolean heatCarrier;
    @Column(name = "geodesy_locations")
    private Integer geodesyLocations;
}