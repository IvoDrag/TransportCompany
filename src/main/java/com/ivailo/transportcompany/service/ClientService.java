package com.ivailo.transportcompany.service;

import com.ivailo.transportcompany.entity.Client;
import com.ivailo.transportcompany.entity.Company;
import com.ivailo.transportcompany.entity.Transportation;
import com.ivailo.transportcompany.entity.Vehicle;
import com.ivailo.transportcompany.repository.ClientRepository;
import com.ivailo.transportcompany.repository.CompanyRepository;
import com.ivailo.transportcompany.repository.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    private final CompanyRepository companyRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, CompanyRepository companyRepository) {
        this.clientRepository = clientRepository;
        this.companyRepository = companyRepository;
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClient(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Client client) {
        Client clientToUpdate = clientRepository.getReferenceById(client.getId());
        clientToUpdate.setClientName(client.getClientName());
        return clientRepository.save(clientToUpdate);
    }

    public String delete(Long id) {
        clientRepository.deleteById(id);
        return "Client with id [" + id + "] has been successfully deleted!";
    }

    public Client assignCompanyToClient(Long clientId, Long companyId) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        Company company = companyRepository.findById(companyId).orElseThrow();
        client.setCompany(company);
        return clientRepository.save(client);
    }
}
