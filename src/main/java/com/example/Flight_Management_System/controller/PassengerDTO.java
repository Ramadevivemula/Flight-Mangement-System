package com.example.Flight_Management_System.controller;

public class PassengerDTO {
    private Long id;
    private String passengerName;

    /**
     * Constructs a new PassengerDTO with the specified ID and passenger name.
     *
     * @param id the unique identifier of the passenger
     * @param passengerName the name of the passenger
     */
    public PassengerDTO(Long id, String passengerName) {
        this.id = id;
        this.passengerName = passengerName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
}
