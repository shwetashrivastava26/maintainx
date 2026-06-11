

package com.maintainx.controller;

import com.maintainx.model.SparePart;
import com.maintainx.service.SparePartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/parts")
@RequiredArgsConstructor
public class SparePartController {

    private final SparePartService partService;

    @GetMapping
    public List<SparePart> getAllParts() {
        return partService.getAllParts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SparePart> getPartById(@PathVariable Long id) {
        return ResponseEntity.ok(partService.getPartById(id));
    }

    @GetMapping("/lowstock")
    public List<SparePart> getLowStockParts() {
        return partService.getLowStockParts();
    }

    @GetMapping("/lowstock/count")
    public ResponseEntity<Long> countLowStock() {
        return ResponseEntity.ok(partService.countLowStock());
    }

    @PostMapping
    public ResponseEntity<SparePart> addPart(
            @Valid @RequestBody SparePart part) {
        return ResponseEntity.ok(partService.addPart(part));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SparePart> updatePart(
            @PathVariable Long id,
            @Valid @RequestBody SparePart part) {
        return ResponseEntity.ok(partService.updatePart(id, part));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePart(@PathVariable Long id) {
        partService.deletePart(id);
        return ResponseEntity.noContent().build();
    }

}
