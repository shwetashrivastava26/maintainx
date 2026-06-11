package com.maintainx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "machines")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Machine code is required")
    @Column(name = "code", nullable = false)
    private String code;

    @NotBlank(message = "Block is required")
    @Column(name = "block", nullable = false)
    private String block;

    @NotBlank(message = "Machine name is required")
    @Column(name = "machine_name", nullable = false)
    private String machine;

    @Column(name = "status", nullable = false)
    private String status = "Active";

}