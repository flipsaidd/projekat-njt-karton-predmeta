package com.fgc.kartonpredmeta.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nastavnici")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nastavnik implements DomainEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false)
    private String zvanje;

    @Column(nullable = false, unique = true)
    private String email;
}
