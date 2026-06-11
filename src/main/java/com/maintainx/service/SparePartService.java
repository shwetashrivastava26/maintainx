package com.maintainx.service;

import com.maintainx.model.SparePart;
import com.maintainx.repository.SparePartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SparePartService {

    private final SparePartRepository partRepository;
    private final AuditLogService auditLogService;

    public List<SparePart> getAllParts() {
        return partRepository.findAll();
    }

    public SparePart getPartById(Long id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part not found with id: " + id));
    }

    public SparePart addPart(SparePart part) {
        SparePart saved = partRepository.save(part);
        auditLogService.log("Part Added : " + saved.getName());
        return saved;
    }

    public SparePart updatePart(Long id, SparePart updated) {
        SparePart existing = getPartById(id);
        existing.setName(updated.getName());
        existing.setStock(updated.getStock());
        existing.setReorder(updated.getReorder());
        existing.setCost(updated.getCost());
        SparePart saved = partRepository.save(existing);
        auditLogService.log("Part Updated : " + saved.getName());
        return saved;
    }

    public void deletePart(Long id) {
        SparePart part = getPartById(id);
        partRepository.deleteById(id);
        auditLogService.log("Part Deleted : " + part.getName());
    }

    public List<SparePart> getLowStockParts() {
        return partRepository.findLowStockParts();
    }

    public long countLowStock() {
        return partRepository.findLowStockParts().size();
    }

}