package com.maintainx.controller;

import com.maintainx.model.Machine;
import com.maintainx.service.MachineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/machines")
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;

    @GetMapping
    public List<Machine> getAllMachines() {
        return machineService.getAllMachines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Machine> getMachineById(@PathVariable Long id) {
        return ResponseEntity.ok(machineService.getMachineById(id));
    }

    @GetMapping("/block/{block}")
    public List<Machine> getMachinesByBlock(@PathVariable String block) {
        return machineService.getMachinesByBlock(block);
    }

    @GetMapping("/status/{status}")
    public List<Machine> getMachinesByStatus(@PathVariable String status) {
        return machineService.getMachinesByStatus(status);
    }

    @PostMapping
    public ResponseEntity<Machine> addMachine(@Valid @RequestBody Machine machine) {
        return ResponseEntity.ok(machineService.addMachine(machine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Machine> updateMachine(
            @PathVariable Long id,
            @Valid @RequestBody Machine machine) {
        return ResponseEntity.ok(machineService.updateMachine(id, machine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable Long id) {
        machineService.deleteMachine(id);
        return ResponseEntity.noContent().build();
    }

}