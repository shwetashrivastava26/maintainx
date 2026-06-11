
package com.maintainx.controller;

import com.maintainx.model.FaultTicket;
import com.maintainx.service.FaultTicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class FaultTicketController {

    private final FaultTicketService ticketService;

    @GetMapping
    public List<FaultTicket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaultTicket> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping("/status/{status}")
    public List<FaultTicket> getTicketsByStatus(@PathVariable String status) {
        return ticketService.getTicketsByStatus(status);
    }

    @GetMapping("/priority/{priority}")
    public List<FaultTicket> getTicketsByPriority(@PathVariable String priority) {
        return ticketService.getTicketsByPriority(priority);
    }

    @GetMapping("/open/count")
    public ResponseEntity<Long> countOpenTickets() {
        return ResponseEntity.ok(ticketService.countOpenTickets());
    }

    @PostMapping
    public ResponseEntity<FaultTicket> addTicket(
            @Valid @RequestBody FaultTicket ticket) {
        return ResponseEntity.ok(ticketService.addTicket(ticket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaultTicket> updateTicket(
            @PathVariable Long id,
            @Valid @RequestBody FaultTicket ticket) {
        return ResponseEntity.ok(ticketService.updateTicket(id, ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

}