package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter departureBeforeNowFilter = new DepartureBeforeNowFilter();
        FlightFilter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
        FlightFilter groundTimeExceedsTwoHoursFilter = new GroundTimeExceedsTwoHoursFilter();

        System.out.println("Original flights:");
        printFlights(flights);

        List<Flight> filteredFlights1 = departureBeforeNowFilter.filter(flights);
        System.out.println("\nFlights after filtering out departure before now:");
        printFlights(filteredFlights1);

        List<Flight> filteredFlights2 = arrivalBeforeDepartureFilter.filter(flights);
        System.out.println("\nFlights after filtering out arrival before departure:");
        printFlights(filteredFlights2);

        List<Flight> filteredFlights3 = groundTimeExceedsTwoHoursFilter.filter(flights);
        System.out.println("\nFlights after filtering out ground time exceeds two hours:");
        printFlights(filteredFlights3);
    }

    private static void printFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println("Flight:");
            for (Segment segment : flight.getSegments()) {
                System.out.println("  Departure: " + segment.getDepartureDate() + " - Arrival: " + segment.getArrivalDate());
            }
        }
    }
}
