package ru.nabokovsg.referencebooks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.referencebooks.model_enum.ParameterCalculationType;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "measurement_parameters_library")
public class MeasurementParameterLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "unit_measurement")
    private String unitMeasurement;
    @Column(name = "calculation_type")
    @Enumerated(EnumType.STRING)
    private ParameterCalculationType calculationType;
    @Column(name = "calculation")
    private String calculation;
    @Column(name = "acceptable_min_mm")
    private Float acceptableMinValueMM;
    @Column(name = "acceptable_min_percentage")
    private Float acceptableMinValuePercentage;
    @Column(name = "acceptable_max_mm")
    private Float acceptableMaxValueMM;
    @Column(name = "acceptable_max_percentage")
    private Float acceptableMaxValuePercentage;
    @Column(name = "calculate_residual_thickness")
    private Boolean calculateByResidualThickness;

    @ManyToOne
    @JoinColumn(name = "defect_id")
    @JsonIgnore
    private DefectLibrary defect;
    @ManyToOne
    @JoinColumn(name = "repair_id")
    @JsonIgnore
    private RepairLibrary repair;

    @Override
    public String toString() {
        return "MeasurementParameterLibrary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitMeasurement='" + unitMeasurement + '\'' +
                ", calculationType=" + calculationType +
                ", calculation='" + calculation + '\'' +
                ", acceptableMinValueMM=" + acceptableMinValueMM +
                ", acceptableMinValuePercentage=" + acceptableMinValuePercentage +
                ", acceptableMaxValueMM=" + acceptableMaxValueMM +
                ", acceptableMaxValuePercentage=" + acceptableMaxValuePercentage +
                ", calculateByResidualThickness=" + calculateByResidualThickness +
                ", defect=" + defect +
                ", repair=" + repair +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementParameterLibrary that = (MeasurementParameterLibrary) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}