package com.example.Flight_Management_System.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightInfoRepository extends JpaRepository<FlightInfo, Long> {
    // Custom query to find flights by passenger name
    // List<FlightInfo> findByPassengerName(String passengerName); // removed, not needed
}

