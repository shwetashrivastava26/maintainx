package com.maintainx.repository;

import com.maintainx.model.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, Long> {

    @Query("SELECT p FROM SparePart p WHERE p.stock <= p.reorder")
    List<SparePart> findLowStockParts();

}