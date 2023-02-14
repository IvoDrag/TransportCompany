package com.ivailo.transportcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.concurrent.ConcurrentMap;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee name can not be blank!")
    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "driver_qualification", nullable = false)
    @Enumerated(EnumType.STRING)
    private DriverQualification driverQualification;

    @Positive
    @DecimalMin(value = "2000", message = "Salary must be more than or equal to 2000")
    @DecimalMax(value = "10000", message = "Salary must be less than or equal to 10000")
    @Column(name = "salary", nullable = false)
    private double salary;

    // DONE
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
}
