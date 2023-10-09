package ua.kpi.eco.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "pollutions", uniqueConstraints = { @UniqueConstraint(columnNames = {"object_id", "pollutant_id", "year" }) })
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

    @Column(name = "pollution_concentration")
    private double pollutionConcentration;

    @Column(name = "add_ladd")
    private double addLadd;

    public static double calculateAddLadd(double pc) {
        final double defaultBW = 60, daysInYear = 365,
                defaultTout = 1.64, defaultTin = 19.69, defaultAT = 70,
                defaultVout = 0.63, defaultVin = 0.5, defaultED = 70;
        return (((pc*defaultTout*defaultVout)+(pc*defaultTin*defaultVin))*daysInYear*defaultED)/(defaultBW*defaultAT*daysInYear);
    }
}
