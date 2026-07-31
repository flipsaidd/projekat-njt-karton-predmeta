package com.fgc.kartonpredmeta.model;

import com.fgc.kartonpredmeta.model.enums.StatusPredmeta;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "predmet_modul")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetModul implements DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modul_id", nullable = false)
    private Modul modul;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPredmeta statusPredmeta;
}
