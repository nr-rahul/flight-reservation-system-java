package service;

import model.Flight;
import model.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class FlightServiceTest {

    FlightService service;

    @BeforeEach
    void setUp() {
        service = new FlightService();
        service.addFlight(new Flight("A1", "Dallas", LocalDateTime.of(2025, 1, 10, 10, 0), 5));
    }

    @Test
    void testSearchFlights() {
        List<Flight> result = service.searchFlights("Dallas", LocalDateTime.of(2025, 1, 10, 0, 0));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void bookFlightSuccess() {
        Flight f = service.searchFlights("Dallas", LocalDateTime.of(2025, 1, 10, 0, 0)).get(0);
        Reservation r = service.bookFlight("John", f, 2);

        Assertions.assertEquals(2, r.getSeatsBooked());
        Assertions.assertEquals(3, f.getAvailableSeats());
    }

    @Test
    void bookingFailsWhenOverSeats() {
        Flight f = service.searchFlights("Dallas", LocalDateTime.of(2025, 1, 10, 0, 0)).get(0);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.bookFlight("John", f, 20)
        );
    }
}
