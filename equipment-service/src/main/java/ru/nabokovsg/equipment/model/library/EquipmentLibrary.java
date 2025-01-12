package ru.nabokovsg.equipment.model.library;

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
@Table(name = "equipments_library")
public class EquipmentLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_name")
    private String equipmentName;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "orientation")
    private String orientation;
    @Column(name = "model")
    private String model;
    @OneToMany(mappedBy = "equipmentLibrary",
               orphanRemoval = true,
               cascade = CascadeType.REMOVE,
               fetch = FetchType.EAGER)
    private Set<ElementLibrary> elements;
}