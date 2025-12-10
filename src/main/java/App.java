import model.Flight;
import model.Reservation;
import service.FlightService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        FlightService flightService = new FlightService();

        // Preload flights
        flightService.addFlight(new Flight("AA101", "New York", LocalDateTime.of(2025, 5, 15, 10, 30), 50));
        flightService.addFlight(new Flight("BB202", "Chicago", LocalDateTime.of(2025, 5, 15, 14, 0), 20));
        flightService.addFlight(new Flight("CC303", "New York", LocalDateTime.of(2025, 5, 15, 18, 45), 10));

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while (true) {
            System.out.println("\n=== Flight Reservation System ===");
            System.out.println("1. Search Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {

                case 1:
                    System.out.print("Enter destination: ");
                    String dest = sc.nextLine();

                    System.out.print("Enter date (yyyy-MM-dd 00:00): ");
                    LocalDateTime date = LocalDateTime.parse(sc.nextLine(), formatter);

                    List<Flight> results = flightService.searchFlights(dest, date);

                    if (results.isEmpty()) {
                        System.out.println("No flights available.");
                    } else {
                        results.forEach(System.out::println);
                    }
                    break;

                case 2:
                    System.out.print("Your Name: ");
                    String customer = sc.nextLine();

                    System.out.print("Flight Number: ");
                    String fnum = sc.nextLine();

                    Flight selected = flightService.searchFlights("New York",
                                    LocalDateTime.of(2025, 5, 15, 0, 0))
                            .stream()
                            .filter(f -> f.getFlightNumber().equalsIgnoreCase(fnum))
                            .findFirst()
                            .orElse(null);

                    if (selected == null) {
                        System.out.println("Flight not found!");
                        break;
                    }

                    System.out.print("Seats to Book: ");
                    int seats = sc.nextInt();
                    sc.nextLine();

                    try {
                        Reservation r = flightService.bookFlight(customer, selected, seats);
                        System.out.println("Booking Successful: " + r);
                    } catch (Exception e) {
                        System.out.println("Booking Failed: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Your Name: ");
                    String name = sc.nextLine();

                    List<Reservation> resv = flightService.getReservations(name);

                    if (resv.isEmpty())
                        System.out.println("No reservations found.");
                    else
                        resv.forEach(System.out::println);

                    break;

                case 4:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
