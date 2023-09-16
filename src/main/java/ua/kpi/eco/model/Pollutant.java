package ua.kpi.eco.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pollutants")
public class Pollutant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tlv")
    private Long tlv;

    @Column(name = "mass_consumption")
    private Long massConsumption;

    @OneToMany(mappedBy = "pollutant")
    private List<Pollution> pollutions;
}
