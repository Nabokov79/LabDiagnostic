package ru.nabokovsg.measurement.model.diagnostics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.measurement.model.qualityControl.WeldDefectControl;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "measured_parameters")
public class MeasuredParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "parameter_id")
    private Long parameterId;
    @Column(name = "parameter_name")
    private String parameterName;
    @Column(name = "value")
    private Double value;
    @Column(name = "unit_measurement")
    private String unitMeasurement;
    @ManyToOne
    @JoinColumn(name = "defect_id")
    @JsonIgnore
    private DefectMeasurement defect;
    @ManyToOne
    @JoinColumn(name = "repair_id")
    @JsonIgnore
    private RepairMeasurement repair;
    @ManyToOne
    @JoinColumn(name = "weld_defect_id")
    @JsonIgnore
    private WeldDefectControl weldDefect;

    @Override
    public String toString() {
        return "MeasuredParameter{" +
                "id=" + id +
                ", parameterId=" + parameterId +
                ", parameterName='" + parameterName + '\'' +
                ", value=" + value +
                ", unitMeasurement='" + unitMeasurement + '\'' +
                '}';
    }
}