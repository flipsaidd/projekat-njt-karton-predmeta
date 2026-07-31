package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.PredmetLiteratura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredmetLiteraturaRepository extends JpaRepository<PredmetLiteratura, Long> {
}
