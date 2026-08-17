package com.fgc.kartonpredmeta.controller;

import com.fgc.kartonpredmeta.dto.PredmetFilterDTO;
import com.fgc.kartonpredmeta.dto.PredmetRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetResponseDTO;
import com.fgc.kartonpredmeta.service.PdfGeneratorService;
import com.fgc.kartonpredmeta.service.PredmetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/predmet")
@RequiredArgsConstructor
public class PredmetController {

    private final PredmetService predmetService;
    private final PdfGeneratorService pdfGeneratorService;

    @GetMapping
    public ResponseEntity<Page<PredmetResponseDTO>> getAll(@ModelAttribute PredmetFilterDTO filter,
                                                           @PageableDefault(size=5,sort="id") Pageable pageable) {
        Page<PredmetResponseDTO> predmetPage = predmetService.findByFilter(filter, pageable);
        return ResponseEntity.ok(predmetPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredmetResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(predmetService.findById(id));
    }

//    @GetMapping
//    public ResponseEntity<List<PredmetResponseDTO>> getByFilter(PredmetFilterDTO filter) {
//        return ResponseEntity.ok(predmetService.findByFilter(filter));
//    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> getPredmetPdf(@PathVariable Long id) throws IOException {
        byte[] pdfBytes = pdfGeneratorService.generisiKartonPredmetaPdf(id);

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "karton_predmeta_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);

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
