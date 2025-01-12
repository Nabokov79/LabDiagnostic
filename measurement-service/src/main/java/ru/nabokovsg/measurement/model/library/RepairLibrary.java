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
@Table(name = "repairs_library")
public class RepairLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "repair_name")
    private String repairName;
    @Column(name = "calculation")
    @Enumerated(EnumType.STRING)
    private ParameterCalculationType calculation;
    @OneToMany(mappedBy = "elementRepair",
               orphanRemoval = true,
               cascade = CascadeType.REMOVE,
               fetch = FetchType.EAGER)
    private Set<MeasurementParameterLibrary> measuredParameters;
}