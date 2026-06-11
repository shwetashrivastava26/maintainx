package com.maintainx.service;

import com.maintainx.model.Machine;
import com.maintainx.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineService {

    private final MachineRepository machineRepository;
    private final AuditLogService auditLogService;

    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    public Machine getMachineById(Long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine not found with id: " + id));
    }

    public Machine addMachine(Machine machine) {
        Machine saved = machineRepository.save(machine);
        auditLogService.log("Machine Added : " + saved.getMachine());
        return saved;
    }

    public Machine updateMachine(Long id, Machine updated) {
        Machine existing = getMachineById(id);
        existing.setCode(updated.getCode());
        existing.setBlock(updated.getBlock());
        existing.setMachine(updated.getMachine());
        existing.setStatus(updated.getStatus());
        Machine saved = machineRepository.save(existing);
        auditLogService.log("Machine Updated : " + saved.getMachine());
        return saved;
    }

    public void deleteMachine(Long id) {
        Machine machine = getMachineById(id);
        machineRepository.deleteById(id);
        auditLogService.log("Machine Deleted ID : " + id + " (" + machine.getMachine() + ")");
    }

    public List<Machine> getMachinesByBlock(String block) {
        return machineRepository.findByBlock(block);
    }

    public List<Machine> getMachinesByStatus(String status) {
        return machineRepository.findByStatus(status);
    }

}