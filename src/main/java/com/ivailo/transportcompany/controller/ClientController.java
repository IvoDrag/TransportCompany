package com.ivailo.transportcompany.controller;

import com.ivailo.transportcompany.entity.Client;
import com.ivailo.transportcompany.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public List<Client> getAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping("/{id}")
    public Client findClientById(@PathVariable Long id) {
        return clientService.findClient(id);
    }

    @PostMapping("/add")
    public Client addClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @PutMapping("/edit")
    public Client editClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        return clientService.delete(id);
    }

    @PutMapping("/{clientId}/company/{companyId}")
    public Client addCompanyToClient(@PathVariable Long clientId, @PathVariable Long companyId) {
        return clientService.assignCompanyToClient(clientId, companyId);
    }
}
