package ru.nabokovsg.measurement.model.qualityControl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weld_defect_control")
public class WeldDefectControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "welded_joint_number")
    private Integer weldedJointNumber;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "defect_name")
    private String defectName;
    @Column(name = "coordinates")
    private String coordinates;
    @Column(name = "quality_assessment")
    private String qualityAssessment;
    @Column(name = "parameters_string")
    private String parametersString;
    @OneToMany(mappedBy = "weldDefect",
               orphanRemoval = true,
               cascade = CascadeType.REMOVE,
               fetch = FetchType.EAGER)
    private Set<MeasuredParameter> measuredParameters;
}