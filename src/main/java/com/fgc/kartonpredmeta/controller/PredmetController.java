package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.PredmetRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetResponseDTO;
import com.fgc.kartonpredmeta.service.PredmetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predmet")
@RequiredArgsConstructor
public class PredmetController {

    private final PredmetService predmetService;

    @GetMapping
    public ResponseEntity<List<PredmetResponseDTO>> getAll() {
        return ResponseEntity.ok(predmetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredmetResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(predmetService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PredmetResponseDTO> create(@Valid @RequestBody PredmetRequestDTO requestDTO) {
        PredmetResponseDTO response = predmetService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PredmetResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PredmetRequestDTO requestDTO) {
        return ResponseEntity.ok(predmetService.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        predmetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
