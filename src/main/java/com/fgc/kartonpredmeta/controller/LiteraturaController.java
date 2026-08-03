package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.LiteraturaDTO;
import com.fgc.kartonpredmeta.service.LiteraturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/literatura")
@RequiredArgsConstructor
public class LiteraturaController {

    private final LiteraturaService literaturaService;

    @PostMapping
    public ResponseEntity<LiteraturaDTO> createLiteratura(@Valid @RequestBody LiteraturaDTO literaturaDTO) {
        LiteraturaDTO literatura=literaturaService.createLiteratura(literaturaDTO);
        return ResponseEntity.ok(literatura);
    }

    @GetMapping
    public ResponseEntity<List<LiteraturaDTO>> getAllLiteratura() {
        List<LiteraturaDTO> literaturaList = literaturaService.getAllLiteratura();
        return ResponseEntity.ok(literaturaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LiteraturaDTO> getLiteraturaById(@PathVariable Long id) {
        LiteraturaDTO literatura = literaturaService.getLiteraturaById(id);
        return ResponseEntity.ok(literatura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LiteraturaDTO> updateLiteratura(@PathVariable Long id, @Valid @RequestBody LiteraturaDTO literaturaDTO) {
        LiteraturaDTO updatedLiteratura = literaturaService.updateLiteratura(id, literaturaDTO);
        return ResponseEntity.ok(updatedLiteratura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLiteratura(@PathVariable Long id) {
        literaturaService.deleteLiteratura(id);
        return ResponseEntity.noContent().build();
    }


}
