package com.example.Flight_Management_System.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a passenger entity in the Flight Management System.
 * Each passenger is associated with a specific flight.
 *
 * <p>
 * Fields:
 * <ul>
 *   <li>id - Unique identifier for the passenger.</li>
 *   <li>passengerName - Name of the passenger.</li>
 *   <li>flight - Reference to the associated FlightInfo entity.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This entity is mapped to the "passenger" table in the database.
 * </p>
 *
 * @author YourName
 */
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    @Column(name = "passenger_name")
    private String passengerName;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private FlightInfo flight;

    public FlightInfo getFlight() {
        return flight;
    }

    public void setFlight(FlightInfo flight) {
        this.flight = flight;
    }

    // getters and setters
}