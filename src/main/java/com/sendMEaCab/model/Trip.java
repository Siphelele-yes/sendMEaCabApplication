package com.sendMEaCab.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "trip")
public class Trip {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private UUID tripId;

    @Column(name = "pickupAddress")
    private String pickupAddress;

    @Column(name = "destinationAddress")
    private String destinationAddress;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driverId", referencedColumnName = "driverId")
    private Driver driverId;

    @Column(name = "tripDate")
    private LocalDate tripDate;
}
