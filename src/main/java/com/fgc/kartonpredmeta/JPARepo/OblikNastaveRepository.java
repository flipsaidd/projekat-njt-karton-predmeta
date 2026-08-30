package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.OblikNastave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OblikNastaveRepository extends JpaRepository<OblikNastave, Long> {
}
