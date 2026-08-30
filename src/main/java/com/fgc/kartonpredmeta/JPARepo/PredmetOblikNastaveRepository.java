package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.PredmetOblikNastave;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PredmetOblikNastaveRepository extends JpaRepository<PredmetOblikNastave, Long> {
}
