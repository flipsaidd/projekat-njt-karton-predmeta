package com.fgc.kartonpredmeta.model;



import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "studijski_programi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudijskiProgram implements DomainEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(length=20)
    private String skracenica;

    @OneToMany(mappedBy = "studijskiProgram",cascade=CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Modul> moduli = new ArrayList<>();

}
