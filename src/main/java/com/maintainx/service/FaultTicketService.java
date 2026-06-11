package com.maintainx.service;

import com.maintainx.model.FaultTicket;
import com.maintainx.repository.FaultTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FaultTicketService {

    private final FaultTicketRepository ticketRepository;
    private final AuditLogService auditLogService;

    public List<FaultTicket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public FaultTicket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
    }

    public FaultTicket addTicket(FaultTicket ticket) {
        FaultTicket saved = ticketRepository.save(ticket);
        auditLogService.log("Ticket Created : " + saved.getMachine());
        return saved;
    }

    public FaultTicket updateTicket(Long id, FaultTicket updated) {
        FaultTicket existing = getTicketById(id);
        existing.setMachine(updated.getMachine());
        existing.setPriority(updated.getPriority());
        existing.setStatus(updated.getStatus());
        FaultTicket saved = ticketRepository.save(existing);
        auditLogService.log("Ticket Updated : FT" + saved.getId());
        return saved;
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
        auditLogService.log("Ticket Deleted ID : FT" + id);
    }

    public long countOpenTickets() {
        return ticketRepository.countByStatus("Open");
    }

    public List<FaultTicket> getTicketsByStatus(String status) {
        return ticketRepository.findByStatus(status);
    }

    public List<FaultTicket> getTicketsByPriority(String priority) {
        return ticketRepository.findByPriority(priority);
    }

}