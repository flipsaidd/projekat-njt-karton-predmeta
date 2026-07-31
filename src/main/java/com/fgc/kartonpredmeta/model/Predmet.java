package com.fgc.kartonpredmeta.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "predmeti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Predmet implements DomainEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false, unique = true)
    private String sifra;

    @Column(nullable = false)
    private Integer espb;

    @Column(nullable = false)
    private Integer godinaStudija;

    @Column(nullable = false)
    private Integer semestar;

    @Column(name = "fond_casova_p", nullable = false)
    private Integer fondCasovaPredavanja;

    @Column(name = "fond_casova_v", nullable = false)
    private Integer fondCasovaVezbe;

    @Column(columnDefinition = "TEXT")
    private String cilj;

    @Column(columnDefinition = "TEXT")
    private String ishodi;

    @Column(columnDefinition = "TEXT")
    private String sadrzajPredavanja;

    @Column(columnDefinition = "TEXT")
    private String sadrzajVezbi;

    @Column(columnDefinition = "TEXT")
    private String nacinPolaganja;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PredmetModul> predmetModuli = new ArrayList<>();

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Angazovanje> angazovanja = new ArrayList<>();

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PredmetnaObaveza> predmetneObaveze = new ArrayList<>();

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PredmetLiteratura> literaturaList = new ArrayList<>();
}
