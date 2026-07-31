package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.PredmetnaObaveza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredmetnaObavezaRepository extends JpaRepository<PredmetnaObaveza, Long> {
}
