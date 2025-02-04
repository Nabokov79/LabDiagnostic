package ru.nabokovsg.measurement.model.diagnostics;

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
@Table(name = "calculation_repair_measurements")
public class CalculationRepairMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "repair_id")
    private Long repairId;
    @Column(name = "element_id")
    private Long elementId;
    @Column(name = "part_element_id")
    private Long partElementId;
    @Column(name = "repair_name")
    private String repairName;
    @Column(name = "element_name")
    private String elementName;
    @Column(name = "part_element_name")
    private String partElementName;
    @Column(name = "parameters_string")
    private String parametersString;
}