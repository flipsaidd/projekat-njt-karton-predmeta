package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Long>, JpaSpecificationExecutor<Predmet> {
    Optional<Predmet> findBySifra(String sifra);

    boolean existsBySifra(String sifra);
}
