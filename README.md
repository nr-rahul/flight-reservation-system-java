# Flight Reservation System (Java, Maven)

A fully functional console-based Flight Reservation System written in Java, designed to demonstrate strong object-oriented programming principles, clean architecture, and test-driven development using JUnit.

---

## ✈️ Features

### ✔ Search Flights  
Search available flights by destination and date using a clean service-layer filter.

### ✔ Book Flights  
Validate seat availability and create reservations safely.

### ✔ View Reservations  
See all reservations made by a specific user.

### ✔ In-Memory Data  
Uses simple lists to simulate a lightweight, non-persistent data store.

### ✔ Unit Tested (JUnit 5)  
Includes tests for:  
- Flight search  
- Booking success  
- Overbooking failure  

---

## 🧱 Architecture
The project follows a clean separation of concerns:

**Model Layer**  
Contains `Flight` and `Reservation`.

**Service Layer**  
Contains `FlightService` handling business logic.

**Application Layer**  
`App.java` provides the console UI.

---

## 🧪 Running Tests

Run all tests:

```bash
mvn test
