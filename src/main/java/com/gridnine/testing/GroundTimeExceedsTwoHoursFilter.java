package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class GroundTimeExceedsTwoHoursFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> calculateGroundTime(flight).compareTo(Duration.ofHours(2)) <= 0)
                .collect(Collectors.toList());
    }

    private Duration calculateGroundTime(Flight flight) {
        List<Segment> segments = flight.getSegments();
        Duration groundTime = Duration.ZERO;
        for (int i = 0; i < segments.size() - 1; i++) {
            LocalDateTime arrival = segments.get(i).getArrivalDate();
            LocalDateTime departure = segments.get(i + 1).getDepartureDate();
            groundTime = groundTime.plus(Duration.between(arrival, departure));
        }
        return groundTime;
    }
}
