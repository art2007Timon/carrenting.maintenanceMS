package com.carrenting.maintenance.service;

import com.carrenting.maintenance.feign.ReservationClient;
import com.carrenting.maintenance.ports.data.Maintenance;
import com.carrenting.maintenance.ports.in.MaintenanceManager;
import com.carrenting.maintenance.ports.out.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceService implements MaintenanceManager {
        private final MaintenanceRepository maintenanceRepository;
        private final ReservationClient reservationClient;

        @Autowired
        public MaintenanceService(MaintenanceRepository maintenanceRepository, ReservationClient reservationClient) {
            this.maintenanceRepository = maintenanceRepository;
            this.reservationClient = reservationClient;
        }

    // FUNC-WART-010 - Planung von Fahrzeugwartungen
    //Erstellung der Wartung, Fahrzeugzuweisung
    @Override
    public Maintenance scheduleMaintenance(Maintenance maintenance) {
        List<Integer> availableCars = reservationClient.getAvailableCars(); //Bekommt vom Microsrvises Reservation die liste mit verfuegbaren Fahrzeugen
        if (availableCars.contains(maintenance.getCarID())) {               //Prueft, ob Auto verfügbar ist
            return maintenanceRepository.save(maintenance);
        } else {
            throw new IllegalStateException("Car is not available for maintenance");
        }
    }

    // FUNC-WART-020 - Verfolgung des Wartungsstatus
    // Aktualisierung des Wartungsstatus _ Kann von einem Werkstaattsmitarbeiter angewendet werden
    @Override
    public Maintenance updateMaintenanceStatus(int maintenanceId, Maintenance maintenance) {
        return maintenanceRepository.findById((long) maintenanceId)
                .map(existingMaintenance -> {
                    existingMaintenance.setStatus(maintenance.getStatus());
                    return maintenanceRepository.save(existingMaintenance);
                })
                .orElseThrow(() -> new RuntimeException("Maintenance not found"));
    }

    // Wartungsdetails nach ID
    @Override
    public Optional<Maintenance> getMaintenanceById(int maintenanceId) {
        return maintenanceRepository.findById((long) maintenanceId);
    }

    // Alle Fahrzeugen die in Wartung befinden
    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }


    // Wartungen nach ID loeschen
    @Override
    public void deleteMaintenance(int maintenanceId) {
        maintenanceRepository.deleteById((long) maintenanceId);
    }
}
