package com.ivailo.transportcompany.controller;

import com.ivailo.transportcompany.entity.Client;
import com.ivailo.transportcompany.entity.Transportation;
import com.ivailo.transportcompany.service.TransportationService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transportation")
public class TransportationController {
    public TransportationService transportationService;

    public TransportationController(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    @GetMapping("/all")
    public List<Transportation> getAllTransportations() {
        return transportationService.findAllTransportations();
    }

    @GetMapping("/{id}")
    public Transportation findTransportationById(@PathVariable Long id) {
        return transportationService.findTransportation(id);
    }

    @PostMapping("/add")
    public Transportation addTransportation(@RequestBody Transportation transportation) {
        return transportationService.saveTransportation(transportation);
    }

    @PutMapping("/edit")
    public Transportation editTransportation(@RequestBody Transportation transportation) {
        return transportationService.updateTransportation(transportation);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTransportation(@PathVariable Long id) {
        return transportationService.delete(id);
    }

    @PutMapping("/{transportationId}/company/{companyId}")
    public Transportation addCompany(@PathVariable Long transportationId, @PathVariable Long companyId) {
        return transportationService.addCompanyToTransportation(transportationId, companyId);
    }

    @PutMapping("/{transportationId}/client/{clientId}")
    public Transportation addClient(@PathVariable Long transportationId, @PathVariable Long clientId) {
        return transportationService.addClientToTransportation(transportationId, clientId);
    }

    @GetMapping("/csv")
    public String parseDataToCSVFile() throws IOException {
        return transportationService.exportTransportationDataToCSVFile();
    }
}
