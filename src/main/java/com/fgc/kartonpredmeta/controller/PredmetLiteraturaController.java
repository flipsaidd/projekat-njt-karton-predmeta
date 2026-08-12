package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.PredmetLiteraturaRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetLiteraturaResponseDTO;
import com.fgc.kartonpredmeta.service.PredmetLiteraturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predmet/{predmetId}/literatura")
@RequiredArgsConstructor
public class PredmetLiteraturaController {

    private final PredmetLiteraturaService predmetLiteraturaService;

    @PostMapping
    public ResponseEntity<PredmetLiteraturaResponseDTO> addPredmetLiteratura(@PathVariable Long predmetId, @Valid @RequestBody PredmetLiteraturaRequestDTO requestDto) {
        PredmetLiteraturaResponseDTO predmetlit=predmetLiteraturaService.dodajLiteraturuNaPredmet(predmetId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(predmetlit);
    }

    @GetMapping
    public ResponseEntity<List<PredmetLiteraturaResponseDTO>> getAllLiteraturaByPredmet(@PathVariable Long predmetId) {
        return ResponseEntity.ok(predmetLiteraturaService.getAllLiteraturaByPredmetId(predmetId));
    }

    @PutMapping("/{predmetLiteraturaId}")
    public ResponseEntity<PredmetLiteraturaResponseDTO> updatePredmetLiteratura(@PathVariable Long predmetId,
                                                                                @PathVariable Long predmetLiteraturaId,
                                                                                @Valid @RequestBody PredmetLiteraturaRequestDTO requestDto) {
        return ResponseEntity.ok(predmetLiteraturaService.updatePredmetLiteratura(predmetId, predmetLiteraturaId, requestDto));
    }

    @DeleteMapping("/{predmetLiteraturaId}")
    public ResponseEntity<Void> deletePredmetLiteratura(@PathVariable Long predmetId, @PathVariable Long predmetLiteraturaId) {
        predmetLiteraturaService.deleteLiteraturaFromPredmet(predmetId, predmetLiteraturaId);
        return ResponseEntity.noContent().build();
    }
}
