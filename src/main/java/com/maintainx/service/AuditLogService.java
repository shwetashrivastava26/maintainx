package com.maintainx.service;

import com.maintainx.model.AuditLog;
import com.maintainx.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public void log(String action) {
        AuditLog entry = new AuditLog();
        entry.setAction(action);
        entry.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(entry);
    }

    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAllByOrderByTimestampDesc();
    }

    public void clearAll() {
        auditLogRepository.deleteAll();
        log("Audit Log Cleared");
    }

}