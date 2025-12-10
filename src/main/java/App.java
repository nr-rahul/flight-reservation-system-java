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
        flightService.addFlight(new Flight("AA102", "New York", LocalDateTime.of(2025, 5, 15, 16, 45), 20));
        flightService.addFlight(new Flight("AA104", "New York", LocalDateTime.of(2025, 5, 17, 21, 15), 30));
        
        flightService.addFlight(new Flight("UA201", "Chicago", LocalDateTime.of(2025, 5, 15, 14, 00), 20));
        flightService.addFlight(new Flight("UA202", "Chicago", LocalDateTime.of(2025, 5, 16, 12, 15), 35));
        flightService.addFlight(new Flight("UA203", "Chicago", LocalDateTime.of(2025, 5, 17, 18, 30), 40));
        
        flightService.addFlight(new Flight("DL303", "Atlanta", LocalDateTime.of(2025, 5, 17, 20, 50), 12));
        
        flightService.addFlight(new Flight("SW401", "Dallas", LocalDateTime.of(2025, 5, 15, 11, 45), 28));
        flightService.addFlight(new Flight("SW403", "Dallas", LocalDateTime.of(2025, 5, 17, 19, 55), 30));
        
        flightService.addFlight(new Flight("JB501", "Boston", LocalDateTime.of(2025, 5, 15, 13, 35), 18));
        flightService.addFlight(new Flight("JB502", "Boston", LocalDateTime.of(2025, 5, 16, 17, 00), 26));
        flightService.addFlight(new Flight("JB503", "Boston", LocalDateTime.of(2025, 5, 17, 06, 45), 10));
        
        flightService.addFlight(new Flight("AL601", "Los Angeles", LocalDateTime.of(2025, 5, 15, 07, 10), 60));
        flightService.addFlight(new Flight("AL602", "Los Angeles", LocalDateTime.of(2025, 5, 16, 13, 40), 45));
        flightService.addFlight(new Flight("AL603", "Los Angeles", LocalDateTime.of(2025, 5, 17, 21, 05), 50));
        
        flightService.addFlight(new Flight("FR701", "San Francisco", LocalDateTime.of(2025, 5, 15, 10, 15), 22));
        flightService.addFlight(new Flight("FR702", "San Francisco", LocalDateTime.of(2025, 5, 16, 14, 30), 30));
        flightService.addFlight(new Flight("FR703", "San Francisco", LocalDateTime.of(2025, 5, 17, 23, 50), 40));
        
        flightService.addFlight(new Flight("SP802", "Seattle", LocalDateTime.of(2025, 5, 16, 16, 10), 24));
        flightService.addFlight(new Flight("SP803", "Seattle", LocalDateTime.of(2025, 5, 17, 20, 00), 15));
        
        flightService.addFlight(new Flight("MI901", "Miami", LocalDateTime.of(2025, 5, 15, 12, 05), 27));
        flightService.addFlight(new Flight("MI902", "Miami", LocalDateTime.of(2025, 5, 16, 18, 20), 36));
        flightService.addFlight(new Flight("MI903", "Miami", LocalDateTime.of(2025, 5, 17, 07, 50), 19));
        
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
