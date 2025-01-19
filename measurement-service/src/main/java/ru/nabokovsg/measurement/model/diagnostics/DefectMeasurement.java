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
@Table(name = "defect_measurements")
public class DefectMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "defect_library_id")
    private Long defectLibraryId;
    @Column(name = "defect_name")
    private String defectName;
    @Column(name = "unacceptable")
    private Boolean unacceptable;
    @Column(name = "use_calculate_thickness")
    private Boolean useCalculateThickness;
    @Column(name = "quality_assessment")
    private String qualityAssessment;
    @Column(name = "element_id")
    private Long elementId;
    @Column(name = "element_name")
    private String elementName;
    @Column(name = "part_element_id")
    private Long partElementId;
    @Column(name = "part_element_name")
    private String partElementName;
    @OneToMany(mappedBy = "defect")
    private Set<MeasuredParameter> measuredParameters;

    @Override
    public String toString() {
        return "DefectMeasurement{" +
                "id=" + id +
                ", equipmentId=" + equipmentId +
                ", defectLibraryId=" + defectLibraryId +
                ", defectName='" + defectName + '\'' +
                ", unacceptable=" + unacceptable +
                ", useCalculateThickness=" + useCalculateThickness +
                ", qualityAssessment='" + qualityAssessment + '\'' +
                ", elementId=" + elementId +
                ", elementName='" + elementName + '\'' +
                ", partElementId=" + partElementId +
                ", partElementName='" + partElementName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefectMeasurement that = (DefectMeasurement) o;
        return Objects.equals(id, that.id) && Objects.equals(equipmentId, that.equipmentId)
                                           && Objects.equals(defectLibraryId, that.defectLibraryId)
                                           && Objects.equals(defectName, that.defectName)
                                           && Objects.equals(unacceptable, that.unacceptable)
                                           && Objects.equals(useCalculateThickness, that.useCalculateThickness)
                                           && Objects.equals(qualityAssessment, that.qualityAssessment)
                                           && Objects.equals(elementId, that.elementId)
                                           && Objects.equals(elementName, that.elementName)
                                           && Objects.equals(partElementId, that.partElementId)
                                           && Objects.equals(partElementName, that.partElementName)
                                           && Objects.equals(measuredParameters, that.measuredParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, equipmentId, defectLibraryId, defectName, unacceptable, useCalculateThickness
                , qualityAssessment, elementId, elementName, partElementId, partElementName, measuredParameters);
    }
}