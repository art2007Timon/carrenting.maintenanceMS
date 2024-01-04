package com.carrenting.maintenance.ports.out;

import com.carrenting.maintenance.ports.data.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    // Дополнительные методы запросов, если необходимы
}
