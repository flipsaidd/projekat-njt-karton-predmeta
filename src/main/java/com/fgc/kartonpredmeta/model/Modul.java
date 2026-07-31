package com.fgc.kartonpredmeta.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "moduli")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Modul implements DomainEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studijski_program_id", nullable = false)
    private StudijskiProgram studijskiProgram;
}
