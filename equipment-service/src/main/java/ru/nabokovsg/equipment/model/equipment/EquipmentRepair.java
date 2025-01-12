package ru.nabokovsg.equipment.model.equipment;

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
@Table(name = "equipment_repairs")
public class EquipmentRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private String date;
    @Column(name = "description")
    private String description;
    @Column(name = "organization")
    private String organization;
    @Column(name = "equipment_id")
    private Long equipmentId;
}