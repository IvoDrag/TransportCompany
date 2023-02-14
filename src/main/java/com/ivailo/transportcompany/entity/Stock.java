package com.ivailo.transportcompany.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Stock name can not be blank!")
    @Column(name = "stock_name", nullable = false)
    private String stockName;

    @Positive
    @Column(name = "weight", nullable = false)
    private double weight;

    // DONE
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportation_id", referencedColumnName = "id")
    private Transportation transportation;
}
