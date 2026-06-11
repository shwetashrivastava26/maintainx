package com.maintainx.service;

import com.maintainx.model.MaintenanceLog;
import com.maintainx.repository.MaintenanceLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceLogService {

    private final MaintenanceLogRepository logRepository;
    private final AuditLogService auditLogService;

    public List<MaintenanceLog> getAllLogs() {
        return logRepository.findAll();
    }

    public MaintenanceLog getLogById(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found with id: " + id));
    }

    public MaintenanceLog addLog(MaintenanceLog log) {
        MaintenanceLog saved = logRepository.save(log);
        auditLogService.log("Maintenance Log Added : " + saved.getMachine());
        return saved;
    }

    public MaintenanceLog updateLog(Long id, MaintenanceLog updated) {
        MaintenanceLog existing = getLogById(id);
        existing.setMachine(updated.getMachine());
        existing.setDate(updated.getDate());
        existing.setTechnician(updated.getTechnician());
        existing.setCost(updated.getCost());
        existing.setStatus(updated.getStatus());
        MaintenanceLog saved = logRepository.save(existing);
        auditLogService.log("Maintenance Log Updated : " + saved.getMachine());
        return saved;
    }

    public void deleteLog(Long id) {
        logRepository.deleteById(id);
        auditLogService.log("Maintenance Log Deleted ID : " + id);
    }

    public Double getTotalCost() {
        return logRepository.getTotalCost();
    }

    public List<MaintenanceLog> getLogsByMachine(String machine) {
        return logRepository.findByMachine(machine);
    }

}