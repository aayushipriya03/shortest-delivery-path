package com.deliverysystem.service;


import com.deliverysystem.model.Location;
import com.deliverysystem.model.Path;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeCalculator {
    private static final double EARTH_RADIUS_KM = 6371.0; // Earth radius in kilometers

    /**
     * returns total time of a particular route
     */
    public static double calculateTotalTime(Path route, double pt1, double pt2) {
        double totalTime = 0;
        for (int i = 0; i < route.getLocations().size() - 1; i++) {
            Location current = route.getLocations().get(i);
            Location next = route.getLocations().get(i + 1);
            totalTime += calculateTime(current, next);
        }
        return totalTime + pt1 + pt2; // average time of making food for pt1 and pt2 is added to total time
    }

    /**
     * returns time taken between location one to location two
     */
    private static double calculateTime(Location location1, Location location2) {
        return calculateDistance(location1.getLatitude(), location1.getLongitude(), location2.getLatitude(),
                location2.getLongitude()) / 20.0;
    }

    /**
     * returns distance between location one to location two using haversine formula
     */
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.pow(Math.sin(deltaLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    /**
     * returns minimum Time taken to deliver the order to respective customer by delivery person (aman)
     */
    public static double shortestDeliveryTime(List<Path> routes, double pt1, double pt2) {
        double shortestTime = Double.MAX_VALUE;
        for (Path route : routes) {
            double totalTime = calculateTotalTime(route, pt1, pt2);
            shortestTime = Math.min(shortestTime, totalTime);
        }
        return shortestTime;
    }

}
