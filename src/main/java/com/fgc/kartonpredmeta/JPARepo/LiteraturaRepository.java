package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.Literatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiteraturaRepository extends JpaRepository<Literatura, Long> {
}
