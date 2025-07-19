package com.example.Flight_Management_System.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Data Transfer Object representing the response details of a flight.
 * <p>
 * Contains flight information such as flight ID, name, arrival and departure dates,
 * travelling city, and an optional list of passengers.
 * </p>
 *
 * <ul>
 *   <li><b>flightId</b>: Unique identifier for the flight.</li>
 *   <li><b>flightName</b>: Name of the flight.</li>
 *   <li><b>arrivalDate</b>: Scheduled arrival date of the flight.</li>
 *   <li><b>departureDate</b>: Scheduled departure date of the flight.</li>
 *   <li><b>travellingCity</b>: City to which the flight is travelling.</li>
 *   <li><b>passengers</b>: List of passengers on the flight (may be null).</li>
 * </ul>
 *
 * <p>
 * The passengers list is included in the response only if it is not null.
 * </p>
 */
public class FlightResponseDTO {
    private Long flightId;
    private String flightName;
    private String arrivalDate;
    private String departureDate;
    private String travellingCity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PassengerDTO> passengers;

    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getFlightName() { return flightName; }
    public void setFlightName(String flightName) { this.flightName = flightName; }
    public String getArrivalDate() { return arrivalDate; }
    public void setArrivalDate(String arrivalDate) { this.arrivalDate = arrivalDate; }
    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
    public String getTravellingCity() { return travellingCity; }
    public void setTravellingCity(String travellingCity) { this.travellingCity = travellingCity; }
    public List<PassengerDTO> getPassengers() { return passengers; }
    public void setPassengers(List<PassengerDTO> passengers) { this.passengers = passengers; }
}
