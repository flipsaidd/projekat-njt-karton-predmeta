package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.Angazovanje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AngazovanjeRepository extends JpaRepository<Angazovanje, Long> {
}
