package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.ModulDTO;
import com.fgc.kartonpredmeta.service.ModulService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modul")
@RequiredArgsConstructor
public class ModulController {

    private final ModulService modulService;

    @PostMapping
    public ResponseEntity<ModulDTO> createModul(@RequestBody ModulDTO modulDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modulService.createModul(modulDTO));
    }

    @GetMapping
    public ResponseEntity<List<ModulDTO>> getAllModuli() {
        return ResponseEntity.ok(modulService.getAllModuli());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModulDTO> getModulById(@PathVariable Long id) {
        return ResponseEntity.ok(modulService.getModulById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModulDTO> updateModul(@PathVariable Long id, @RequestBody ModulDTO modulDTO) {
        return ResponseEntity.ok(modulService.updateModul(id, modulDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModul(@PathVariable Long id) {
        modulService.deleteModul(id);
        return ResponseEntity.noContent().build();
    }
}
