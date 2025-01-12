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
@Table(name = "equipment_passport")
public class EquipmentPassport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "header")
    private String header;
    @Column(name = "meaning")
    private String meaning;
    @Column(name = "use_to_protocol")
    private Boolean useToProtocol;
    @Column(name = "equipment_id")
    private Long equipmentId;
}