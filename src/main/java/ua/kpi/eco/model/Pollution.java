package ua.kpi.eco.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "pollutions")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pollution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pollutions_seq")
    @SequenceGenerator(name = "pollutions_seq", sequenceName = "pollutions_sequence", initialValue = 15, allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "object_id")
    private Object object;

    @ManyToOne
    @JoinColumn(name = "pollutant_id")
    private Pollutant pollutant;

    @Column(name = "year")
    private int year;

    @Column(name = "value_pollution")
    private double valuePollution;
}