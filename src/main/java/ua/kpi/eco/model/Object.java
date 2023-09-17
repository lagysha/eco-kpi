package ua.kpi.eco.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "objects")
@NoArgsConstructor
public class Object {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "object", cascade = {CascadeType.REMOVE})
    private List<Pollution> pollutions;

    public Object(String name) {
        this.name = name;
    }
}
