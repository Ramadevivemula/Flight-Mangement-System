package com.example.Flight_Management_System.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entity class representing flight information in the Flight Management System.
 * Maps to the "flight_info" table in the database.
 *
 * <p>Fields:</p>
 * <ul>
 *   <li>flightId - Unique identifier for the flight (Primary Key).</li>
 *   <li>flightName - Name of the flight.</li>
 *   <li>arrivalDate - Date of arrival for the flight.</li>
 *   <li>departureDate - Date of departure for the flight.</li>
 *   <li>travellingCity - City to which the flight is travelling.</li>
 *   <li>passengers - List of passengers associated with the flight.</li>
 * </ul>
 *
 * <p>Relationships:</p>
 * <ul>
 *   <li>One-to-many relationship with {@code Passenger} entity.</li>
 * </ul>
 *
 * <p>Includes standard getters and setters for all fields.</p>
 */
@Entity
@Table(name = "flight_info")
public class FlightInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_ID")
    private Integer flightId;

    @Column(name = "flight_name")
    private String flightName;

    @Column(name = "Arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "Departure_date")
    private LocalDate departureDate;

    @Column(name = "travelling_city")
    private String travellingCity;


    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    // Getter and Setter for passengers
    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
    // Getters and Setters
    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getTravellingCity() {
        return travellingCity;
    }

    public void setTravellingCity(String travellingCity) {
        this.travellingCity = travellingCity;
    }
}
