package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Long> {
    Optional<Predmet> findBySifra(String sifra);

    boolean existsBySifra(String sifra);
}
