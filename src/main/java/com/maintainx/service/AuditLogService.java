package com.maintainx.service;

import com.maintainx.model.AuditLog;
import com.maintainx.repository.AuditLogRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void log(String action) {
        AuditLog entry = new AuditLog();
        entry.setAction(action);
        entry.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(entry);
    }

    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAllByOrderByTimestampDesc();
    }

    @Transactional
    public void clearAll() {
        auditLogRepository.deleteAll();
        entityManager.createNativeQuery(
            "ALTER SEQUENCE audit_logs_id_seq RESTART WITH 1"
        ).executeUpdate();
    }

}