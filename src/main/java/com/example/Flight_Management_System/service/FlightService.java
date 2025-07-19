package com.example.Flight_Management_System.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Flight_Management_System.entity.FlightInfo;
import com.example.Flight_Management_System.entity.FlightInfoRepository;

@Service
public class FlightService {
    private final FlightInfoRepository flightInfoRepository;

    @Autowired
    public FlightService(FlightInfoRepository flightInfoRepository) {
        this.flightInfoRepository = flightInfoRepository;
    }

    public List<FlightInfo> getAllFlights() {
        return flightInfoRepository.findAll();
    }

    public Optional<FlightInfo> getFlightById(Long id) {
        return flightInfoRepository.findById(id);
    }

    public FlightInfo saveFlight(FlightInfo flightInfo) {
        // Validate required fields
        if (flightInfo.getFlightName() == null || flightInfo.getFlightName().isEmpty() ||
            flightInfo.getArrivalDate() == null ||
            flightInfo.getDepartureDate() == null ||
            flightInfo.getTravellingCity() == null || flightInfo.getTravellingCity().isEmpty()) {
            throw new IllegalArgumentException("All flight details (flight_name, arrival_date, departure_date, travelling_city) must be provided.");
        }
        return flightInfoRepository.save(flightInfo);
    }

    public void deleteFlight(Long id) {
        flightInfoRepository.deleteById(id);
    }

}
