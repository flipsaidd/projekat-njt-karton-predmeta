package com.fgc.kartonpredmeta.model;

import com.fgc.kartonpredmeta.model.enums.VrstaObaveze;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "predmetne_obaveze")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetnaObaveza implements DomainEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private Integer maxPoena;

    @Enumerated (EnumType.STRING)
    @Column(nullable = false)
    private VrstaObaveze vrstaObaveze;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;
}
