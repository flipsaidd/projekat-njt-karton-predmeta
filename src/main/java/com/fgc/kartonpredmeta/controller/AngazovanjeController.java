package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.AngazovanjeRequestDTO;
import com.fgc.kartonpredmeta.dto.AngazovanjeResponseDTO;
import com.fgc.kartonpredmeta.service.AngazovanjeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predmet/{predmetId}/angazovanje")
@RequiredArgsConstructor
public class AngazovanjeController {

    private final AngazovanjeService angazovanjeService;

    @PostMapping
    public ResponseEntity<AngazovanjeResponseDTO> addAngazovanje(@PathVariable Long predmetId, @Valid @RequestBody AngazovanjeRequestDTO angazovanjeDTO) {
        AngazovanjeResponseDTO createdAngazovanje = angazovanjeService.dodajAngazovanje(predmetId, angazovanjeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAngazovanje);
    }

    @GetMapping
    public ResponseEntity<List<AngazovanjeResponseDTO>> getAngazovanjaZaPredmet(@PathVariable Long predmetId) {
        return ResponseEntity.ok(angazovanjeService.getAllAngazovanjaByPredmetId(predmetId));
    }

    @PutMapping("/{angazovanjeId}")
    public ResponseEntity<AngazovanjeResponseDTO> updateAngazovanje(@PathVariable Long predmetId,
                                                                    @PathVariable Long angazovanjeId,
                                                                    @Valid @RequestBody AngazovanjeRequestDTO angazovanjeDTO) {
        return ResponseEntity.ok(angazovanjeService.updateAngazovanje(predmetId, angazovanjeId, angazovanjeDTO));
    }

    @DeleteMapping("/{angazovanjeId}")
    public ResponseEntity<Void> deleteAngazovanje(@PathVariable Long predmetId, @PathVariable Long angazovanjeId) {
        angazovanjeService.deleteAngazovanje(predmetId, angazovanjeId);
        return ResponseEntity.noContent().build();
    }
}
