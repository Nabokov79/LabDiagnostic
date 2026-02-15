package ru.nabokovsg.referencebooks.model;

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
@Table(name = "parts_element_library")
public class PartElementLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "part_full_name")
    private String partElementFullName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "name")
    private String name;
    @Column(name = "place")
    private String place;
    @Column(name = "diameter")
    private Integer diameter;
    @Column(name = "length")
    private Integer length;
    @Column(name = "height")
    private Integer height;
    @Column(name = "width")
    private Integer width;
    @Column(name = "diameter_size")
    private Integer diameterSize;
    @Column(name = "thickness_size")
    private Double thicknessSize;
    @Column(name = "standard_size")
    private String standardSize;
    @Column(name = "dimensions")
    private String dimensions;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "element_id")
    private ElementLibrary element;
}