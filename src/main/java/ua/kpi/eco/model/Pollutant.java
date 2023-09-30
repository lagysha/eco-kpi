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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pollution_seq")
    @SequenceGenerator(name = "pollution_seq", sequenceName = "pollutions_sequence", initialValue = 15, allocationSize = 1)
    private Long id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "tlv")
    private Long elv;

    @Column(name = "mfr")
    private Long mfr;
}
