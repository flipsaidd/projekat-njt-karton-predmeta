package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.Nastavnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NastavnikRepository extends JpaRepository<Nastavnik, Long> {
    Optional<Nastavnik> findByEmail(String email);
}
