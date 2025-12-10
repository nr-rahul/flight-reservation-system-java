package service;

import model.Flight;
import model.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    private final List<Flight> flights = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public List<Flight> searchFlights(String destination, LocalDateTime date) {
        return flights.stream()
                .filter(f -> f.getDestination().equalsIgnoreCase(destination)
                        && f.getDepartureTime().toLocalDate().equals(date.toLocalDate())
                        && f.getAvailableSeats() > 0)
                .collect(Collectors.toList());
    }

    public Reservation bookFlight(String customerName, Flight flight, int seats) {
        if (flight.getAvailableSeats() < seats) {
            throw new IllegalArgumentException("Not enough seats available.");
        }

        flight.reduceSeats(seats);

        Reservation reservation = new Reservation(customerName, flight, seats);
        reservations.add(reservation);

        return reservation;
    }

    public List<Reservation> getReservations(String customerName) {
        return reservations.stream()
                .filter(r -> r.getCustomerName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }
}
