package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.AngazovanjeRequestDTO;
import com.fgc.kartonpredmeta.dto.AngazovanjeResponseDTO;
import com.fgc.kartonpredmeta.dto.PredmetModulRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetModulResponseDTO;
import com.fgc.kartonpredmeta.service.PredmetModulService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predmet/{predmetId}/pm")
@RequiredArgsConstructor
public class PredmetModulController {

    private final PredmetModulService predmetModulService;

    @PostMapping
    public ResponseEntity<PredmetModulResponseDTO> addPredmetModul(@PathVariable Long predmetId, @Valid @RequestBody PredmetModulRequestDTO predmetModulDTO) {
        PredmetModulResponseDTO createdPredmetModul = predmetModulService.dodajPredmetModul(predmetId, predmetModulDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPredmetModul);
    }

    @GetMapping
    public ResponseEntity<List<PredmetModulResponseDTO>> getPredmetModuliZaPredmet(@PathVariable Long predmetId) {
        return ResponseEntity.ok(predmetModulService.getAllModuliByPredmetId(predmetId));
    }

    @PutMapping("/{predmetModulId}")
    public ResponseEntity<PredmetModulResponseDTO> updatePredmetModul(@PathVariable Long predmetId,
                                                                      @PathVariable Long predmetModulId,
                                                                      @Valid @RequestBody PredmetModulRequestDTO predmetModulDTO) {
        return ResponseEntity.ok(predmetModulService.updatePredmetModul(predmetId, predmetModulId, predmetModulDTO));
    }

    @DeleteMapping("/{modulId}")
    public ResponseEntity<Void> deletePredmetModul(@PathVariable Long predmetId, @PathVariable Long modulId) {
        predmetModulService.deletePredmetModul(predmetId, modulId);
        return ResponseEntity.noContent().build();
    }
}
