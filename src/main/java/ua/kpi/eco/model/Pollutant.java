package ua.kpi.eco.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pollutants")
@Getter
@Setter
public class Pollutant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tlv")
    private Long tlv;

    @Column(name = "mfr")
    private Long mfr;
}
