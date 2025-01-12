package ru.nabokovsg.document.model;

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
@Table(name = "header_templates")
public class HeaderTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "organization_name")
    private String organizationName;
    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "area_name")
    private String heatSupplyAreaName;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "exploitation_region_name")
    private String exploitationRegionName;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "fax")
    private String fax;
    @Column(name = "email")
    private String email;
}