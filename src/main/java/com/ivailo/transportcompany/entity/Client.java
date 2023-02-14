package com.ivailo.transportcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Client name can not be blank!")
    @Column(name = "client_name", nullable = false)
    private String clientName;

    // DONE
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    @Column(name = "transportation_list")
    private Set<Transportation> transportationList;
}
