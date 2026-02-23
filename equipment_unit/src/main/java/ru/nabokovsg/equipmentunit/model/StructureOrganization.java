package ru.nabokovsg.equipmentunit.model;

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
@Table(name = "structure_organization")
public class StructureOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="branch_id")
    private Long branchId;
    @Column(name="branch")
    private String branch;
    @Column(name="department_id")
    private Long departmentId;
    @Column(name="department")
    private String department;
    @Column(name="heat_supply_site_id")
    private Long heatSupplySiteId;
    @Column(name="heat_supply_site")
    private String heatSupplySite;
    @Column(name="technical_device_id")
    private Long technicalDeviceId;
    @Column(name="technical_device")
    private String technicalDevice;
    @Column(name="heat_supply_source_id")
    private Long heatSupplySourceId;
    @Column(name="heat_supply_source")
    private String heatSupplySource;
}