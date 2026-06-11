// package com.maintainx.controller;

// import com.maintainx.model.AuditLog;
// import com.maintainx.service.AuditLogService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/audit")
// @RequiredArgsConstructor
// public class AuditLogController {

//     private final AuditLogService auditLogService;

//     @GetMapping
//     public List<AuditLog> getAllLogs() {
//         return auditLogService.getAllLogs();
//     }

//     @DeleteMapping("/clear")
//     public ResponseEntity<Void> clearAll() {
//         auditLogService.clearAll();
//         return ResponseEntity.noContent().build();
//     }

// }

package com.maintainx.controller;

import com.maintainx.model.AuditLog;
import com.maintainx.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogService auditLogService;

    @GetMapping
    public List<AuditLog> getAllLogs() {
        return auditLogService.getAllLogs();
    }

    @PostMapping
    public ResponseEntity<AuditLog> addLog(@RequestBody AuditLog log) {
        auditLogService.log(log.getAction());
        return ResponseEntity.ok(log);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearAll() {
        auditLogService.clearAll();
        return ResponseEntity.noContent().build();
    }

}