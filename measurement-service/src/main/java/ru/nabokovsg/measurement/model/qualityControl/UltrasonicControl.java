package ru.nabokovsg.measurement.model.qualityControl;

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
@Table(name = "ultrasonic_control")
public class UltrasonicControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "welded_joint_number")
    private Integer weldedJointNumber;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "description_defect")
    private String descriptionDefect;
    @Column(name = "coordinates")
    private String coordinates;
    @Column(name = "quality_assessment")
    private String qualityAssessment;
}