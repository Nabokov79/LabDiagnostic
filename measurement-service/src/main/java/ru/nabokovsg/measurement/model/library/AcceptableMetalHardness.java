package ru.nabokovsg.measurement.model.library;

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
@Table(name = "acceptable_metal_hardness")
public class AcceptableMetalHardness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_library_id")
    private Long equipmentLibraryId;
    @Column(name = "element_library_id")
    private Long elementLibraryId;
    @Column(name = "part_element_library_id")
    private Long partElementLibraryId;
    @Column(name = "acceptable_diameter")
    private Integer minAcceptableDiameter;
    @Column(name = "acceptable_thickness")
    private Double minAcceptableThickness;
    @Column(name = "min_hardness")
    private Integer minAcceptableHardness;
    @Column(name = "max_hardness")
    private Integer maxAcceptableHardness;
    @Column(name = "measurement_error")
    private Float measurementError;

    @Override
    public String toString() {
        return "AcceptableMetalHardness{" +
                "id=" + id +
                ", equipmentLibraryId=" + equipmentLibraryId +
                ", elementLibraryId=" + elementLibraryId +
                ", partElementLibraryId=" + partElementLibraryId +
                ", minAcceptableDiameter=" + minAcceptableDiameter +
                ", minAcceptableThickness=" + minAcceptableThickness +
                ", minAcceptableHardness=" + minAcceptableHardness +
                ", maxAcceptableHardness=" + maxAcceptableHardness +
                ", measurementError=" + measurementError +
                '}';
    }
}