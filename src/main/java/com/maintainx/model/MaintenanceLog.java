package com.maintainx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "maintenance_logs")
public class MaintenanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Machine name is required")
    @Column(name = "machine", nullable = false)
    private String machine;

    @NotNull(message = "Date is required")
    @Column(name = "log_date", nullable = false)
    private LocalDate date;

    @NotBlank(message = "Technician is required")
    @Column(name = "technician", nullable = false)
    private String technician;

    @NotNull(message = "Cost is required")
    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "status", nullable = false)
    private String status = "Completed";

}