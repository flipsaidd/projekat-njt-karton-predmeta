package com.fgc.kartonpredmeta.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "literature")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Literatura implements DomainEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naslov;

    @Column(nullable = false)
    private String autori;

    @Column
    private Integer godinaIzdanja;

    @Column
    private String izdavac;

    @Column
    private String isbn;

}
