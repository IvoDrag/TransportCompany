package com.ivailo.transportcompany.controller;

import com.ivailo.transportcompany.entity.Company;
import com.ivailo.transportcompany.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@PathVariable Long id) {
        return companyService.findCompany(id);
    }

    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyService.findAllCompanies();
    }

    @PostMapping("/add")
    public Company addCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @PutMapping("/edit")
    public Company editCompany(@RequestBody Company company) {
        return companyService.updateCompany(company);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        return companyService.delete(id);
    }
}
