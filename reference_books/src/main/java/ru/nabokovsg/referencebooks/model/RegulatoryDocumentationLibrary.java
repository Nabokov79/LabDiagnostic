package ru.nabokovsg.referencebooks.model;

import jakarta.persistence.*;
import lombok.*;
import ru.nabokovsg.referencebooks.model_enum.RegulatoryDocumentationLibraryStatus;
import ru.nabokovsg.referencebooks.model_enum.RegulatoryDocumentationLibraryType;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "regulatory_documentation_library")
public class RegulatoryDocumentationLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "document")
    private String document;
    @Column(name = "document_name")
    private String documentName;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RegulatoryDocumentationLibraryType type;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RegulatoryDocumentationLibraryStatus status;
    @Column(name = "document_status")
    private String documentStatus;
    @Column(name = "area_distribution")
    private String areaDistribution;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "documentations_equipments",
            joinColumns =  {@JoinColumn(name = "document_id")},
            inverseJoinColumns = {@JoinColumn(name = "equipment_id")})
    @ToString.Exclude
    private Set<EquipmentLibrary> equipments;
}