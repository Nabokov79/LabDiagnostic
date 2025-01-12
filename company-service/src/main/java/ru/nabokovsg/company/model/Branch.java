package ru.nabokovsg.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "branch",
              orphanRemoval = true,
              cascade = CascadeType.REMOVE,
              fetch = FetchType.EAGER)
    private List<HeatSupplyArea> heatSupplyAreas;
    @OneToMany(mappedBy = "branch",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private List<Department> departments;
    @OneToMany(mappedBy = "branch",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private List<ExploitationRegion> exploitationRegions;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    @JsonIgnore
    private Organization organization;
}