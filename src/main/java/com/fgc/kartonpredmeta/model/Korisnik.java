package com.fgc.kartonpredmeta.model;

import com.fgc.kartonpredmeta.model.enums.Uloga;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "korisnici")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Korisnik implements DomainEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Uloga uloga;
}
