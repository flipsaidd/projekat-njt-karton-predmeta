package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.PredmetModul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredmetModulRepository extends JpaRepository<PredmetModul, Long> {
    boolean existsByModulId(Long id);
}
