package ru.nabokovsg.laboratoryqc.model;

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
@Table(name = "measuring_tools")
public class MeasuringTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "toll_name")
    private String tollName;
    @Column(name = "model")
    private String model;
    @Column(name = "work_number")
    private String workNumber;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "manufacturing")
    private LocalDate manufacturing;
    @Column(name = "exploitation")
    private LocalDate exploitation;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "measuring_range")
    private String  measuringRange;
    @Column(name = "characteristics")
    private String characteristics;
    @Column(name = "owner")
    private String owner;
    @Column(name = "verification_date")
    private LocalDate verificationDate;
    @Column(name = "next_verification_date")
    private LocalDate nextVerificationDate;
    @Column(name = "organization")
    private String organization;
    @Column(name = "certificate_number")
    private String certificateNumber;
    @Column(name = "registry")
    private String registry;
    @Column(name = "note")
    private String note;
    @Column(name = "control_name")
    private String controlName;
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;
}