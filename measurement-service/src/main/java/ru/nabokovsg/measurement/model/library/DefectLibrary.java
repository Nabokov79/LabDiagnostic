package ru.nabokovsg.measurement.model.library;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "defects_library")
public class DefectLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "defect_name")
    private String defectName;
    @Column(name = "unacceptable")
    private Boolean unacceptable;
    @Column(name = "use_calculate_thickness")
    private Boolean useCalculateThickness;
    @Column(name = "calculation")
    @Enumerated(EnumType.STRING)
    private ParameterCalculationType calculation;
    @OneToMany(mappedBy = "defect",
               orphanRemoval = true,
               cascade = CascadeType.REMOVE,
               fetch = FetchType.EAGER)
    private Set<MeasurementParameterLibrary> measuredParameters;
}