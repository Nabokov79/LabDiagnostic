package ru.nabokovsg.referencebooks.model;

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
@Table(name = "employees")
public class EmployeeLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "branch_id")
    private Long branchId;
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "source_id")
    private Long sourceId;
    @Column(name = "branch")
    private String branch;
    @Column(name = "department")
    private String department;
    @Column(name = "source")
    private String source;
    @Column(name = "initials")
    private String initials;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "surname")
    private String surname;
    @Column(name = "post")
    private String post;
    @Column(name = "email")
    private String email;
}