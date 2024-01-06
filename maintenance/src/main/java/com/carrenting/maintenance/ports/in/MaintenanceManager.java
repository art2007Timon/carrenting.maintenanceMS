package com.carrenting.maintenance.ports.in;

import com.carrenting.maintenance.ports.data.Maintenance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface MaintenanceManager {
    // ------------------------------[FUNC-WART-010 - Planung von Fahrzeugwartungen]-------------------------------------
    //Erstellung der Wartung, Fahrzeugzuweisung
    Maintenance scheduleMaintenance(Maintenance maintenance);


    // ------------------------------[FUNC-WART-020 - Verfolgung des Wartungsstatus]-------------------------------------

    // Aktualisierung des Wartungsstatus _ Kann von einem Werkstaattsmitarbeiter angewendet werden
    Maintenance updateMaintenanceStatus(int maintenanceId, Maintenance maintenance);

    // Wartungsdetails nach ID
    Optional<Maintenance> getMaintenanceById(int maintenanceId);

    // Alle Fahrzeugen die in Wartung befinden
    List<Maintenance> getAllMaintenances();

    // Wartungen nach ID loeschen
    void deleteMaintenance(int maintenanceId);

}
