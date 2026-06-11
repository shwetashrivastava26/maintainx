package com.maintainx.repository;

import com.maintainx.model.MaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Long> {

    List<MaintenanceLog> findByMachine(String machine);

    @Query("SELECT COALESCE(SUM(l.cost), 0) FROM MaintenanceLog l")
    Double getTotalCost();

}