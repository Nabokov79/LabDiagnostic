package ru.nabokovsg.equipment.model.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "elements")
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "element_library_id")
    private Long elementLibraryId;
    @Column(name = "element_name")
    private String elementName;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "thickness")
    private Double thickness;
    @Column(name = "min_diameter")
    private Integer minDiameter;
    @Column(name = "min_thickness")
    private Double minThickness;
    @Column(name = "max_diameter")
    private Integer maxDiameter;
    @Column(name = "max_thickness")
    private Double maxThickness;
    @OneToMany(mappedBy = "element",
               orphanRemoval = true,
               cascade = CascadeType.REMOVE,
               fetch = FetchType.EAGER)
    private Set<PartElement> partsElement;
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    @JsonIgnore
    private Equipment equipment;
}