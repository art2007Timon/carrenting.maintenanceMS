package com.carrenting.maintenance.feign;

import com.carrenting.maintenance.dto.CarDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

//interaktionen mit dem MS reservation
@FeignClient(name = "reservation-service", url = "http://localhost:8083")
public interface ReservationClient {
    @GetMapping("/api/reservation/availableVehicle")
    List<CarDto> getAvailableVehicle();
}
