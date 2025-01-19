package ru.nabokovsg.document.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "header_templates")
public class PageTitleTemplate {

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
    @Column(name = "type")
    private String type;
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    private String date;
    @Column(name = "equipment_name")
    private String equipmentName;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "fax")
    private String fax;
    @Column(name = "email")
    private String email;
    @Column(name = "post")
    private String post;
    @Column(name = "chief")
    private String chief;
    @Column(name = "city")
    private String city;
}