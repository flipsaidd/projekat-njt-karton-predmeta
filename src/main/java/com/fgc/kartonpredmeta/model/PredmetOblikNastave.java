package com.fgc.kartonpredmeta.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="predmet_oblik")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetOblikNastave implements DomainEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="oblik_nastave_id")
    private OblikNastave oblikNastave;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="predmet_id")
    private Predmet predmet;

    @Column(nullable = false)
    private Integer fondCasova;
}
