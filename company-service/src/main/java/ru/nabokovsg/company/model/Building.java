package ru.nabokovsg.company.model;

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
@Table(name = "buildings")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "building_type")
    private String buildingType;
    @Column(name = "login")
    private String login;
    @Column(name = "address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonIgnore
    private ExploitationRegion exploitationRegion;
}