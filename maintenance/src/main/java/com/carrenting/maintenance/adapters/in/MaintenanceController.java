package com.carrenting.maintenance.adapters.in;

import com.carrenting.maintenance.ports.data.Maintenance;
import com.carrenting.maintenance.ports.in.MaintenanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final MaintenanceManager maintenanceManager;

    @Autowired
    public MaintenanceController(MaintenanceManager maintenanceManager) {
        this.maintenanceManager = maintenanceManager;
    }


    // ------------------------------[FUNC-WART-010 - Planung von Fahrzeugwartungen]-------------------------------------
    //Erstellung der Wartung, Fahrzeugzuweisung
    //POST: /api/maintenance
    //Body: { "carID": 1, "startDate": "2023-01-01", "endDate": "2023-01-03" }
    //Geht nicht -> muss in der Reservierung Liste mit verfuegbaren Autos implementiert werden (getAvailableCars())
    @PostMapping
    public ResponseEntity<Maintenance> scheduleMaintenance(@RequestBody Maintenance maintenance) {
        Maintenance newMaintenance = maintenanceManager.scheduleMaintenance(maintenance);
        return ResponseEntity.ok(newMaintenance);
    }






    // ------------------------------[FUNC-WART-020 - Verfolgung des Wartungsstatus]-------------------------------------

    // Aktualisierung des Wartungsstatus _ Kann von einem Werkstaattsmitarbeiter angewendet werden
    //PUT: /api/maintenance/1
    //Body: { "status": "Kontrolle" }
    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenanceStatus(@PathVariable int id, @RequestBody Maintenance maintenance) {
        Maintenance updatedMaintenance = maintenanceManager.updateMaintenanceStatus(id, maintenance);
        return ResponseEntity.ok(updatedMaintenance);
    }

    // Wartungsdetails nach ID
    //GET: /api/maintenance/1
    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable int id) {
        Optional<Maintenance> maintenance = maintenanceManager.getMaintenanceById(id);
        return maintenance.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Alle Fahrzeugen die in Wartung befinden
    //GET: /api/maintenance/
    @GetMapping()
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        List<Maintenance> аllMaintenances = maintenanceManager.getAllMaintenances();
        return ResponseEntity.ok(аllMaintenances);
    }

    //Wartungen nach ID loeschen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable int id) {
        maintenanceManager.deleteMaintenance(id);
        return ResponseEntity.ok().build();
    }





}
