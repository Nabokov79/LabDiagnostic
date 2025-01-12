package ru.nabokovsg.measurement.model.diagnostics;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repair_measurements")
public class RepairMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "repair_library_id")
    private Long repairLibraryId;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "element_id")
    private Long elementId;
    @Column(name = "part_element_id")
    private Long partElementId;
    @Column(name = "repair_name")
    private String repairName;
    @OneToMany(mappedBy = "repair",
               orphanRemoval = true,
               cascade = CascadeType.REMOVE,
               fetch = FetchType.EAGER)
    private Set<MeasuredParameter> measuredParameters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairMeasurement that = (RepairMeasurement) o;
        return Objects.equals(id, that.id) && Objects.equals(repairLibraryId, that.repairLibraryId)
                                           && Objects.equals(equipmentId, that.equipmentId)
                                           && Objects.equals(elementId, that.elementId)
                                           && Objects.equals(partElementId, that.partElementId)
                                           && Objects.equals(repairName, that.repairName)
                                           && Objects.equals(measuredParameters, that.measuredParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, repairLibraryId, equipmentId, elementId, partElementId, repairName, measuredParameters);
    }
}