package ua.kpi.eco.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "pollutions")
public class Pollution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "object_id")
    private Object object;

    @ManyToOne
    @JoinColumn(name = "pollutant_id")
    private Pollutant pollutant;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "value_pollution")
    private Long valuePollution;
}
