package com.maintainx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "spare_parts")
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Part name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Stock is required")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @NotNull(message = "Reorder level is required")
    @Column(name = "reorder_level", nullable = false)
    private Integer reorder;

    @NotNull(message = "Cost is required")
    @Column(name = "cost", nullable = false)
    private Double cost;

    @Transient
    public String getStatus() {
        return (stock <= reorder) ? "Low Stock" : "Available";
    }

}