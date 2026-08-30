package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.PredmetOblikNastaveRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetOblikNastaveResponseDTO;
import com.fgc.kartonpredmeta.service.PredmetOblikNastaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predmet/{predmetId}/oblik")
@RequiredArgsConstructor
public class PredmetOblikNastaveController {

    private final PredmetOblikNastaveService predmetOblikNastaveService;

    @PostMapping
    public ResponseEntity<PredmetOblikNastaveResponseDTO> addPredmetOblik(
            @PathVariable Long predmetId,
            @Valid @RequestBody PredmetOblikNastaveRequestDTO requestDTO) {
        PredmetOblikNastaveResponseDTO createdOblik = predmetOblikNastaveService.dodajPredmetOblik(predmetId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOblik);
    }

    @GetMapping
    public ResponseEntity<List<PredmetOblikNastaveResponseDTO>> getObliciNastaveZaPredmet(
            @PathVariable Long predmetId) {
        return ResponseEntity.ok(predmetOblikNastaveService.getAllObliciNastaveByPredmetId(predmetId));
    }

    @PutMapping("/{predmetOblikId}")
    public ResponseEntity<PredmetOblikNastaveResponseDTO> updatePredmetOblikNastave(
            @PathVariable Long predmetId,
            @PathVariable Long predmetOblikId,
            @Valid @RequestBody PredmetOblikNastaveRequestDTO requestDTO) {
        return ResponseEntity.ok(predmetOblikNastaveService.updatePredmetOblikNastave(predmetId, predmetOblikId, requestDTO));
    }

    @DeleteMapping("/{predmetOblikId}")
    public ResponseEntity<Void> deletePredmetOblikNastave(
            @PathVariable Long predmetId,
            @PathVariable Long predmetOblikId) {
        predmetOblikNastaveService.deletePredmetOblikNastave(predmetId, predmetOblikId);
        return ResponseEntity.noContent().build();
    }
}
