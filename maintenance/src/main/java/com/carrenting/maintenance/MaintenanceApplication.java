package com.carrenting.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Interaktion mit anderen MSs
public class MaintenanceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MaintenanceApplication.class, args);
	}
}
