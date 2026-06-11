package com.maintainx.controller;

import com.maintainx.model.MaintenanceLog;
import com.maintainx.service.MaintenanceLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class MaintenanceLogController {

    private final MaintenanceLogService logService;

    @GetMapping
    public List<MaintenanceLog> getAllLogs() {
        return logService.getAllLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceLog> getLogById(@PathVariable Long id) {
        return ResponseEntity.ok(logService.getLogById(id));
    }

    @GetMapping("/machine/{machine}")
    public List<MaintenanceLog> getLogsByMachine(@PathVariable String machine) {
        return logService.getLogsByMachine(machine);
    }

    @GetMapping("/totalcost")
    public ResponseEntity<Double> getTotalCost() {
        return ResponseEntity.ok(logService.getTotalCost());
    }

    @PostMapping
    public ResponseEntity<MaintenanceLog> addLog(
            @Valid @RequestBody MaintenanceLog log) {
        return ResponseEntity.ok(logService.addLog(log));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceLog> updateLog(
            @PathVariable Long id,
            @Valid @RequestBody MaintenanceLog log) {
        return ResponseEntity.ok(logService.updateLog(id, log));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        logService.deleteLog(id);
        return ResponseEntity.noContent().build();
    }

}