package com.fgc.kartonpredmeta.JPARepo;

import com.fgc.kartonpredmeta.model.StudijskiProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudijskiProgramRepository extends JpaRepository<StudijskiProgram, Long> {
}
