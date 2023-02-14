package com.ivailo.transportcompany.service;

import com.ivailo.transportcompany.entity.Client;
import com.ivailo.transportcompany.entity.Company;
import com.ivailo.transportcompany.entity.Vehicle;
import com.ivailo.transportcompany.repository.CompanyRepository;
import com.ivailo.transportcompany.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    private final CompanyRepository companyRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, CompanyRepository companyRepository) {
        this.vehicleRepository = vehicleRepository;
        this.companyRepository = companyRepository;
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle findVehicle(Long id) {
        return vehicleRepository.findById(id).orElseThrow();
    }

    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        Vehicle vehicleToBeUpdated = vehicleRepository.getReferenceById(vehicle.getId());
        vehicleToBeUpdated.setVehicleModel(vehicle.getVehicleModel());
        vehicleToBeUpdated.setTypeOfVehicle(vehicle.getTypeOfVehicle());
        return vehicleRepository.save(vehicleToBeUpdated);
    }

    public Vehicle addCompanyToVehicle(Long vehicleId, Long companyId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow();
        Company company = companyRepository.findById(companyId).orElseThrow();
        vehicle.setCompany(company);
        return vehicleRepository.save(vehicle);
    }

    public String delete(Long id) {
        vehicleRepository.deleteById(id);
        return "Vehicle with id [" + id + "] has been successfully deleted!";
    }
}
