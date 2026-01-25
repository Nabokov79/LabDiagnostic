package ru.nabokovsg.referencebooks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repairs_library")
public class RepairLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "without_naming_parameter")
    private Boolean withoutNamingParameter;
    @Column(name = "measured_parameters")
    private String measuredParameters;
    @OneToMany(mappedBy = "repair",
               orphanRemoval = true,
               cascade = CascadeType.REMOVE,
               fetch = FetchType.EAGER)
    private List<MeasurementParameterLibrary> measuredParametersLibrary;
}