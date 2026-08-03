package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.StudijskiProgramDTO;
import com.fgc.kartonpredmeta.service.StudijskiProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sp")
@RequiredArgsConstructor
public class StudijskiProgramController {

    private final StudijskiProgramService studijskiProgramService;

    @PostMapping
    public ResponseEntity<StudijskiProgramDTO> createStudijskiProgram(@Valid @RequestBody StudijskiProgramDTO studijskiProgramDTO) {
        StudijskiProgramDTO sp = studijskiProgramService.createStudijskiProgram(studijskiProgramDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudijskiProgramDTO> getStudijskiProgram(@PathVariable Long id) {
        StudijskiProgramDTO sp = studijskiProgramService.getStudijskiProgram(id);
        return ResponseEntity.ok(sp);
    }

    @GetMapping
    public ResponseEntity<List<StudijskiProgramDTO>> getAllStudijskiProgrami() {
        List<StudijskiProgramDTO> spList = studijskiProgramService.getAllStudijskiProgrami();
        return ResponseEntity.ok(spList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudijskiProgramDTO> updateStudijskiProgram(@PathVariable Long id, @Valid @RequestBody StudijskiProgramDTO studijskiProgramDTO) {
        StudijskiProgramDTO updatedSP = studijskiProgramService.updateStudijskiProgram(id, studijskiProgramDTO);
        return ResponseEntity.ok(updatedSP);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudijskiProgram(@PathVariable Long id) {
        studijskiProgramService.deleteStudijskiProgram(id);
        return ResponseEntity.noContent().build();
    }
}
