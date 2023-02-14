package com.ivailo.transportcompany.controller;

import com.ivailo.transportcompany.entity.Employee;
import com.ivailo.transportcompany.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    public EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployee(id);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @PutMapping("/edit")
    public Employee editEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return employeeService.delete(id);
    }

    @PutMapping("/{employeeId}/company/{companyId}")
    public Employee addCompany(@PathVariable Long employeeId, @PathVariable Long companyId) {
        return employeeService.addCompanyToEmployee(employeeId, companyId);
    }
}
