package com.maintainx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "fault_tickets")
public class FaultTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Machine name is required")
    @Column(name = "machine", nullable = false)
    private String machine;

    @Column(name = "priority", nullable = false)
    private String priority = "Low";

    @Column(name = "status", nullable = false)
    private String status = "Open";

}
