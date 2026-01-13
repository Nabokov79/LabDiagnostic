package ru.nabokovsg.referencebooks.model;

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
@Table(name = "heat_supply_sites")
public class HeatSupplySiteLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_description")
    private String fullDescription;
    @Column(name = "short_description")
    private String shortDescription;
    @OneToMany(mappedBy = "site",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private List<TechnicalDevice> devices;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id")
    @JsonIgnore
    private HeatSupplySourceLibrary source;
}