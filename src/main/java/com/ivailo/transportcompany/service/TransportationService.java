package com.ivailo.transportcompany.service;

import com.ivailo.transportcompany.entity.Client;
import com.ivailo.transportcompany.entity.Company;
import com.ivailo.transportcompany.entity.Transportation;
import com.ivailo.transportcompany.repository.ClientRepository;
import com.ivailo.transportcompany.repository.CompanyRepository;
import com.ivailo.transportcompany.repository.TransportationRepository;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class TransportationService {

    private final static Logger logger = LoggerFactory.getLogger(TransportationService.class);
    private final TransportationRepository transportationRepository;

    private final CompanyRepository companyRepository;

    private final ClientRepository clientRepository;

    @Autowired
    public TransportationService(TransportationRepository transportationRepository, CompanyRepository companyRepository,
                                 ClientRepository clientRepository) {
        this.transportationRepository = transportationRepository;
        this.companyRepository = companyRepository;
        this.clientRepository = clientRepository;
    }

    public Transportation findTransportation(Long id) {
        return transportationRepository.findById(id).orElseThrow();
    }

    public List<Transportation> findAllTransportations() {
        return transportationRepository.findAll();
    }

    public Transportation saveTransportation(Transportation transportation) {
        return transportationRepository.save(transportation);
    }

    public Transportation updateTransportation(Transportation transportation) {
        Transportation transportationToBeUpdated = transportationRepository.getReferenceById(transportation.getId());
        transportationToBeUpdated.setStartingPoint(transportation.getStartingPoint());
        transportationToBeUpdated.setEndingPoint(transportation.getEndingPoint());
        transportationToBeUpdated.setDepartureDate(transportation.getDepartureDate());
        transportationToBeUpdated.setArrivalDate(transportation.getArrivalDate());
        transportationToBeUpdated.setTypeOfTransport(transportation.getTypeOfTransport());
        transportationToBeUpdated.setPrice(transportation.getPrice());
        transportationToBeUpdated.setTransportationPaid(transportation.isTransportationPaid());
        return transportationRepository.save(transportationToBeUpdated);
    }

    public String delete(Long id) {
        transportationRepository.deleteById(id);
        return "Transportation with id [" + id + "] has been successfully deleted!";
    }

    public Transportation addCompanyToTransportation(Long transportationId, Long companyId) {
        Transportation transportation = transportationRepository.findById(transportationId).orElseThrow();
        Company company = companyRepository.findById(companyId).orElseThrow();
        transportation.setCompany(company);
        return transportationRepository.save(transportation);
    }

    public Transportation addClientToTransportation(Long transportationId, Long clientId) {
        Transportation transportation = transportationRepository.findById(transportationId).orElseThrow();
        Client client = clientRepository.findById(clientId).orElseThrow();
        transportation.setClient(client);
        return transportationRepository.save(transportation);
    }

    public String exportTransportationDataToCSVFile() throws IOException {
        Path pathToCSVFile = Paths.get("C:", "Users", "ivad", "GitHub", "TransportationData.csv");
        if(Files.exists(pathToCSVFile)) {
            Files.delete(pathToCSVFile);
        }
        List<Transportation> transportationData = transportationRepository.findAll();
        logger.info("transportationData -> {}", transportationData);
        char csv_separator = ';';
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(pathToCSVFile.toFile(), StandardCharsets.UTF_8, true))) {
            fileWriter.append("id").append(csv_separator)
                    .append("departure_date").append(csv_separator)
                    .append("arrival_date").append(csv_separator)
                    .append("starting_point").append(csv_separator)
                    .append("ending_point").append(csv_separator)
                    .append("price").append(csv_separator)
                    .append("type_of_transport").append(csv_separator)
                    .append("company_id").append(csv_separator)
                    .append("transportation_paid").append(csv_separator)
                    .append("client_id").append(System.lineSeparator());
            transportationData.forEach(transportation -> {
                try {
                    fileWriter.append(transportation.getId().toString()).append(csv_separator)
                            .append(transportation.getDepartureDate().toString()).append(csv_separator)
                            .append(transportation.getArrivalDate().toString()).append(csv_separator)
                            .append(transportation.getStartingPoint()).append(csv_separator)
                            .append(transportation.getEndingPoint()).append(csv_separator)
                            .append(String.valueOf(transportation.getPrice())).append(csv_separator)
                            .append(transportation.getTypeOfTransport().toString()).append(csv_separator)
                            .append(transportation.getCompany().toString()).append(csv_separator)
                            .append(String.valueOf(transportation.isTransportationPaid())).append(csv_separator)
                            .append(transportation.getClient().toString()).append(System.lineSeparator());
                } catch (IOException e) {
                    logger.error("IOException encountered while parsing Transportation data to CSV file!");
                }
            });
        }
        return String.format("Transportation data has been parsed to CSV File located in: %s", pathToCSVFile);
    }
}
