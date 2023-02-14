package com.ivailo.transportcompany.service;

import com.ivailo.transportcompany.entity.Company;
import com.ivailo.transportcompany.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company findCompany(Long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Company company) {
        Company companyToUpdate = companyRepository.getReferenceById(company.getId());
        companyToUpdate.setId(company.getId());
        companyToUpdate.setCompanyName(company.getCompanyName());
        return companyRepository.save(companyToUpdate);
    }

    public String delete(Long id) {
        companyRepository.deleteById(id);
        return "Company with id [" + id + "] has been successfully deleted!";
    }
}
