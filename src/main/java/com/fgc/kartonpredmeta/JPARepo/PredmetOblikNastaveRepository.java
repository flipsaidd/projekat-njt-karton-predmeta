package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.PredmetOblikNastave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredmetOblikNastaveRepository extends JpaRepository<PredmetOblikNastave, Long> {
}
