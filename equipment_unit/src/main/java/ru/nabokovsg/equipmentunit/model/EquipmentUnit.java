package ru.nabokovsg.equipmentunit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipment_units")
public class EquipmentUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="equipment_library_id")
    private Long equipmentLibraryId;
    @Column(name="full_name")
    private String fullName;
    @Column(name="stationary_number")
    private Integer stationaryNumber;
    @Column(name="room")
    private String room;
    @Column(name="geodesy_locations")
    private Integer geodesyLocations;
    @Column(name="date_commissioning")
    private LocalDate dateCommissioning;
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
    @OneToMany(mappedBy = "equipment")
    private Set<Element> elements;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "structure_id")
    @JsonIgnore
    private StructureOrganization structure;
}