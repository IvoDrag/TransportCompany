package com.ivailo.transportcompany.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_model", nullable = false)
    private String vehicleModel;

    @Column(name = "type_of_vehicle", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOfVehicle typeOfVehicle;

    // DONE
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
}
