package ru.nabokovsg.referencebooks.model;

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
@Table(name = "metal_hardness_library")
public class MetalHardnessLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "diameter")
    private Double diameter;
    @Column(name = "thickness")
    private Double thickness;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "min_hardness")
    private Integer minAcceptableHardness;
    @Column(name = "max_hardness")
    private Integer maxAcceptableHardness;
    @Column(name = "measurement_error")
    private Float measurementError;
    @ManyToOne
    @JoinColumn(name = "documentation_id")
    @JsonIgnore
    private RegulatoryDocumentationLibrary documentation;
    @ManyToOne
    @JoinColumn(name = "element_id")
    @JsonIgnore
    private ElementLibrary element;
    @ManyToOne
    @JoinColumn(name = "part_element_id")
    @JsonIgnore
    private PartElementLibrary partElement;
}