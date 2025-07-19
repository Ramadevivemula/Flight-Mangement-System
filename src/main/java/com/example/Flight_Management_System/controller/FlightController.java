package com.example.Flight_Management_System.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
/**
 * Controller class for handling flight-related HTTP requests.
 * Provides endpoints for retrieving flight information.
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Flight_Management_System.entity.FlightInfo;
import com.example.Flight_Management_System.entity.Passenger;
import com.example.Flight_Management_System.service.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(FlightController.class);

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Get all available flights
    @GetMapping("/all")
    public ResponseEntity<List<FlightResponseDTO>> getAllFlights() {
        logger.info("Fetching all available flights");
        List<FlightInfo> flights = flightService.getAllFlights();
        logger.info("Total flights found: {}", flights.size());
        if (flights.isEmpty()) {
            logger.warn("No flights available");
            return ResponseEntity.noContent().build();
        }
        List<FlightResponseDTO> flightDTOs = flights.stream().map(flight -> {
            FlightResponseDTO dto = new FlightResponseDTO();
            dto.setFlightId(flight.getFlightId() != null ? flight.getFlightId().longValue() : null);
            dto.setFlightName(flight.getFlightName());
            dto.setArrivalDate(flight.getArrivalDate() != null ? flight.getArrivalDate().toString() : null);
            dto.setDepartureDate(flight.getDepartureDate() != null ? flight.getDepartureDate().toString() : null);
            dto.setTravellingCity(flight.getTravellingCity());
            // Do NOT set passengers for all flights
            return dto;
        }).toList();
        return ResponseEntity.ok(flightDTOs);
    }


    // Get flight by ID
    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> getFlightById(@PathVariable Long id) {
        logger.info("Fetching flight with ID: {}", id);
        Optional<FlightInfo> flightOpt = flightService.getFlightById(id);
        if (flightOpt.isEmpty()) {
            logger.warn("Flight with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
        FlightInfo flight = flightOpt.get();
        FlightResponseDTO dto = new FlightResponseDTO();
        dto.setFlightId(flight.getFlightId() != null ? flight.getFlightId().longValue() : null);
        dto.setFlightName(flight.getFlightName());
        dto.setArrivalDate(flight.getArrivalDate() != null ? flight.getArrivalDate().toString() : null);
        dto.setDepartureDate(flight.getDepartureDate() != null ? flight.getDepartureDate().toString() : null);
        dto.setTravellingCity(flight.getTravellingCity());
        if (flight.getPassengers() != null) {
            dto.setPassengers(
                flight.getPassengers().stream()
                    .map(p -> new PassengerDTO(p.getId(), p.getPassengerName()))
                    .toList()
            );
        }
        return ResponseEntity.ok(dto);
    }

    // Get flights by passenger name
    @GetMapping("/passenger/{passengerName}")
    public ResponseEntity<List<FlightInfo>> getFlightsByPassengerName(@PathVariable String passengerName) {
        logger.info("Fetching flights for passenger: {}", passengerName);
        List<FlightInfo> flights = flightService.getAllFlights().stream()
            .filter(flight -> flight.getPassengers() != null &&
                flight.getPassengers().stream().anyMatch(p -> passengerName.equalsIgnoreCase(p.getPassengerName())))
            .toList();
        if (flights.isEmpty()) {
            logger.warn("No flights found for passenger: {}", passengerName);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(flights);
    }

    // Add a new flight
    @PostMapping
    public ResponseEntity<String> addFlight(@RequestBody FlightInfo flightInfo) {
        logger.info("Adding new flight: {}", flightInfo);
        FlightInfo savedFlight = flightService.saveFlight(flightInfo);
        logger.info("Flight saved with ID: {}", savedFlight.getFlightId());
        return ResponseEntity.ok("Flight added successfully");
    }

    // Update flight details (no response body, just success message)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFlight(@PathVariable Long id, @RequestBody FlightInfo flightInfo) {
        logger.info("Updating flight with ID: {}", id);
        Optional<FlightInfo> updatedFlight = flightService.getFlightById(id);
        if (updatedFlight.isEmpty()) {
            logger.warn("Flight with ID {} not found for update", id);
            return ResponseEntity.notFound().build();
        }
        FlightInfo existingFlight = updatedFlight.get();
        existingFlight.setFlightName(flightInfo.getFlightName());
        existingFlight.setArrivalDate(flightInfo.getArrivalDate());
        existingFlight.setDepartureDate(flightInfo.getDepartureDate());
        existingFlight.setTravellingCity(flightInfo.getTravellingCity());
        if (flightInfo.getPassengers() != null) {
            flightInfo.getPassengers().forEach(p -> p.setFlight(existingFlight));
            existingFlight.setPassengers(flightInfo.getPassengers());
        }
        flightService.saveFlight(existingFlight);
        logger.info("Flight updated: {}", id);
        return ResponseEntity.ok("Flight updated successfully");
    }

    // Suggestion: To get all passengers for a flight by flight ID
    // Add this endpoint:
    @GetMapping("/{id}/passengers")
    public ResponseEntity<List<Passenger>> getPassengersByFlightId(@PathVariable Long id) {
        logger.info("Fetching passengers for flight ID: {}", id);
        Optional<FlightInfo> flight = flightService.getFlightById(id);
        if (flight.isPresent()) {
            List<Passenger> passengers = flight.get().getPassengers();
            if (passengers == null || passengers.isEmpty()) {
                logger.warn("No passengers found for flight ID: {}", id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(passengers);
        } else {
            logger.warn("Flight with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Remove (delete) flight details
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        logger.info("Deleting flight with ID: {}", id);
        Optional<FlightInfo> flight = flightService.getFlightById(id);
        if (flight.isPresent()) {
            flightService.deleteFlight(id);
            logger.info("Flight with ID {} deleted", id);
            return ResponseEntity.ok("Flight deleted successfully");
        } else {
            logger.warn("Flight with ID {} not found for deletion", id);
            return ResponseEntity.notFound().build();
        }
    }
}
