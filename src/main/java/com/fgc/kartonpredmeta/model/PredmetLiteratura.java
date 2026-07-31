package com.fgc.kartonpredmeta.model;

import com.fgc.kartonpredmeta.model.enums.VrstaLiterature;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "predmet_literatura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetLiteratura implements DomainEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "literatura_id", nullable = false)
    private Literatura literatura;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VrstaLiterature vrstaLiterature;
}
