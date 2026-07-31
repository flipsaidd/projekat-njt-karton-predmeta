package com.fgc.kartonpredmeta.model;

import com.fgc.kartonpredmeta.model.enums.UlogaNastavnika;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "angazovanja")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Angazovanje implements DomainEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nastavnik_id", nullable = false)
    private Nastavnik nastavnik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UlogaNastavnika ulogaNastavnika;

}
