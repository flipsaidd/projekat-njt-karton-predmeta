package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.OblikNastaveDTO;
import com.fgc.kartonpredmeta.service.OblikNastaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oblik")
@RequiredArgsConstructor
public class OblikNastaveController {

    private final OblikNastaveService oblikNastaveService;

    @PostMapping
    public ResponseEntity<OblikNastaveDTO> createOblikNastave(@Valid @RequestBody OblikNastaveDTO dto) {
        OblikNastaveDTO oblik=oblikNastaveService.createOblikNastave(dto);
        return ResponseEntity.ok(oblik);
    }

    @GetMapping
    public ResponseEntity<List<OblikNastaveDTO>> getAllOblici() {
        List<OblikNastaveDTO> oblikList = oblikNastaveService.getAllOblikNastave();
        return ResponseEntity.ok(oblikList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OblikNastaveDTO> getOblikById(@PathVariable Long id) {
        OblikNastaveDTO oblik = oblikNastaveService.getOblikNastave(id);
        return ResponseEntity.ok(oblik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OblikNastaveDTO> updateOblik(@PathVariable Long id, @Valid @RequestBody OblikNastaveDTO oblikDTO) {
        OblikNastaveDTO updatedOblik = oblikNastaveService.updateOblikNastave(id, oblikDTO);
        return ResponseEntity.ok(updatedOblik);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLiteratura(@PathVariable Long id) {
        oblikNastaveService.deleteOblikNastave(id);
        return ResponseEntity.noContent().build();
    }
}
