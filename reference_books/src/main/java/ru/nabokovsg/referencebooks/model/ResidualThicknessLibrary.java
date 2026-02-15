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
@Table(name = "thickness_library")
public class ResidualThicknessLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "diameter")
    private Double diameter;
    @Column(name = "thickness")
    private Double thickness;
    @Column(name = "acceptable_thickness_mm")
    private Double  minAcceptableThicknessMM;
    @Column(name = "acceptable_thickness_percent")
    private Integer minAcceptableThicknessPercent;
    @Column(name = "acceptable_thinning_mm")
    private Double  maxAcceptableThinningMM;
    @Column(name = "acceptable_thinning_percent")
    private Integer maxAcceptableThinningPercent;
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