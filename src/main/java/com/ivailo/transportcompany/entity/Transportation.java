package com.ivailo.transportcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transportation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Transportation starting point can not be blank!")
    @Column(name = "starting_point", nullable = false)
    private String startingPoint;

    @NotBlank(message = "Transportation ending point can not be blank!")
    @Column(name = "ending_point", nullable = false)
    private String endingPoint;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "type_of_transport")
    private TypeOfTransport typeOfTransport;

    @Positive
    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "transportation_paid", nullable = false)
    private boolean transportationPaid;

    // DONE
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    // DONE
    @JsonIgnore
    @OneToMany(mappedBy = "transportation")
    private List<Stock> stockList;

    // DONE
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
}
