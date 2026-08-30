package com.fgc.kartonpredmeta.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="oblici_nastave")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OblikNastave implements DomainEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String naziv;

}
