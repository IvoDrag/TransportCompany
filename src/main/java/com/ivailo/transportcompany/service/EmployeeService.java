package com.ivailo.transportcompany.service;

import com.ivailo.transportcompany.entity.Company;
import com.ivailo.transportcompany.entity.Employee;
import com.ivailo.transportcompany.repository.CompanyRepository;
import com.ivailo.transportcompany.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final CompanyRepository companyRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findEmployee(Long id) {
        // TODO
        return employeeRepository.findById(id).orElseThrow();
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        Employee employeeToEdit = employeeRepository.getReferenceById(employee.getId());
        employeeToEdit.setEmployeeName(employee.getEmployeeName());
        employeeToEdit.setCompany(employee.getCompany());
        employeeToEdit.setSalary(employee.getSalary());
        employeeToEdit.setDriverQualification(employee.getDriverQualification());
        return employeeRepository.save(employeeToEdit);
    }

    public String delete(Long id) {
        employeeRepository.deleteById(id);
        return "Employee with id [" + id + "] has been successfully deleted!";
    }

    public Employee addCompanyToEmployee(Long employeeId, Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        employee.setCompany(company);
        return employeeRepository.save(employee);
    }
}
