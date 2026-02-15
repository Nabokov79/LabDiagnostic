package ru.nabokovsg.referencebooks.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipments_library")
public class EquipmentLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_full_name")
    private String equipmentFullName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "model")
    private String model;
    @Column(name = "period_stabilization")
    private Integer periodStabilization;
    @Column(name = "diameter")
    private Integer diameter;
    @Column(name = "length")
    private Integer length;
    @Column(name = "height")
    private Integer height;
    @Column(name = "width")
    private Integer width;
    @Column(name = "dimensions")
    private String dimensions;
    @OneToMany(mappedBy = "equipment",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private Set<ElementLibrary> elements;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "documentations_equipments",
            joinColumns =  {@JoinColumn(name = "equipment_id")},
            inverseJoinColumns = {@JoinColumn(name = "document_id")})
    @ToString.Exclude
    private Set<RegulatoryDocumentationLibrary> documentations;
}