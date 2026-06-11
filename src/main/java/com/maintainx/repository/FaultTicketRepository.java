package com.maintainx.repository;

import com.maintainx.model.FaultTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FaultTicketRepository extends JpaRepository<FaultTicket, Long> {
    List<FaultTicket> findByStatus(String status);
    List<FaultTicket> findByPriority(String priority);
    long countByStatus(String status);
}