package com.ivailo.transportcompany.controller;

import com.ivailo.transportcompany.entity.Vehicle;
import com.ivailo.transportcompany.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/add")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle findVehicleById(@PathVariable Long id) {
        return vehicleService.findVehicle(id);
    }

    @GetMapping("/all")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.findAllVehicles();
    }

    @PutMapping("/edit")
    public Vehicle editVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(vehicle);
    }

    @PutMapping("/{vehicleId}/company/{companyId}")
    public Vehicle addCompany(@PathVariable Long vehicleId, @PathVariable Long companyId) {
        return vehicleService.addCompanyToVehicle(vehicleId, companyId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVehicleById(@PathVariable Long id) {
        return vehicleService.delete(id);
    }
}
