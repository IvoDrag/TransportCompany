package com.ivailo.transportcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company name can not be blank!")
    @Column(name = "company_name")
    private String companyName;

    // DONE
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    @Column(name = "client_list")
    private Set<Client> clientList;

    // DONE
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    @Column(name = "vehicle_list")
    private List<Vehicle> vehicleList;

    // DONE
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    @Column(name = "employee_list")
    private Set<Employee> employeeList;

    // DONE
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    @Column(name = "transportation_list")
    private List<Transportation> transportationList;
}
