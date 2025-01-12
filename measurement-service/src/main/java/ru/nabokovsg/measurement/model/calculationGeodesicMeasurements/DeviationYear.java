package ru.nabokovsg.measurement.model.calculationGeodesicMeasurements;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "deviation_years")
public class DeviationYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "year")
    private Integer year;
    @Column(name = "deviation")
    private Integer deviation;
    @ManyToOne
    @JoinColumn(name = "reference_point_id")
    @JsonIgnore
    private ReferencePoint referencePoint;
}