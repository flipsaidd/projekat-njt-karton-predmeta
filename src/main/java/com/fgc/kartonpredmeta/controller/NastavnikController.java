package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.NastavnikDTO;
import com.fgc.kartonpredmeta.service.NastavnikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nastavnici")
@RequiredArgsConstructor
public class NastavnikController {

    private final NastavnikService nastavnikService;

    @PostMapping
    public ResponseEntity<NastavnikDTO> createNastavnik(@Valid @RequestBody NastavnikDTO nastavnikDTO) {
        NastavnikDTO uspeo=nastavnikService.createNastavnik(nastavnikDTO);
        return new ResponseEntity<>(uspeo, ResponseEntity.ok().build().getStatusCode());
    }

    @GetMapping
    public ResponseEntity<List<NastavnikDTO>> getAllNastavnici() {
        List<NastavnikDTO> nastavnici = nastavnikService.getAllNastavnici();
        return new ResponseEntity<>(nastavnici, ResponseEntity.ok().build().getStatusCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NastavnikDTO> getNastavnikById(@PathVariable Long id) {
        NastavnikDTO nastavnik = nastavnikService.getNastavnikById(id);
        return ResponseEntity.ok(nastavnik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NastavnikDTO> updateNastavnik(@PathVariable Long id, @Valid @RequestBody NastavnikDTO nastavnikDTO) {
        NastavnikDTO updatedNastavnik = nastavnikService.updateNastavnik(id, nastavnikDTO);
        return ResponseEntity.ok(updatedNastavnik);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNastavnik(@PathVariable Long id) {
        nastavnikService.deleteNastavnik(id);
        return ResponseEntity.noContent().build();
    }
}
