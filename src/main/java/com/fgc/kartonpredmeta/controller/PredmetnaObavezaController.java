package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.PredmetnaObavezaRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetnaObavezaResponseDTO;
import com.fgc.kartonpredmeta.service.PredmetnaObavezaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predmet/{predmetId}/obaveza")
@RequiredArgsConstructor
public class PredmetnaObavezaController {

    private final PredmetnaObavezaService predmetnaObavezaService;

    @PostMapping("/add")
    public ResponseEntity<PredmetnaObavezaResponseDTO> addPredmetnaObaveza(@PathVariable Long predmetId,
                                                                           @Valid @RequestBody PredmetnaObavezaRequestDTO requestDTO) {
        PredmetnaObavezaResponseDTO createdObaveza = predmetnaObavezaService.addPredmetnaObaveza(predmetId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObaveza);
    }

    @GetMapping
    public ResponseEntity<List<PredmetnaObavezaResponseDTO>> getPredmetneObavezeZaPredmet(@PathVariable Long predmetId) {
        return ResponseEntity.ok(predmetnaObavezaService.getAllPredmetneObavezeByPredmetId(predmetId));
    }

    @PutMapping("/{obavezaId}")
    public ResponseEntity<PredmetnaObavezaResponseDTO> updatePredmetnaObaveza(@PathVariable Long predmetId,
                                                                              @PathVariable Long obavezaId,
                                                                              @Valid @RequestBody PredmetnaObavezaRequestDTO requestDTO) {
        return ResponseEntity.ok(predmetnaObavezaService.updatePredmetnaObaveza(predmetId, obavezaId, requestDTO));
    }

    @DeleteMapping("/{obavezaId}")
    public ResponseEntity<Void> deletePredmetnaObaveza(@PathVariable Long predmetId, @PathVariable Long obavezaId) {
        predmetnaObavezaService.deletePredmetnaObaveza(predmetId, obavezaId);
        return ResponseEntity.noContent().build();
    }
}
