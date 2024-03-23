package com.deliverysystem.service;

import com.deliverysystem.model.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

import static com.deliverysystem.enums.LocationType.*;

@Service
public class PathGenerator {


    /**
     * returns all flirted routes which contains the paths
     */
    public static List<Path> generateRoutes(List<Location> locations, DeliveryPerson aman,
                                            Map<Location, Customer> customerMap, Map<Location, Restaurant> restaurantMap) {
        List<Path> routes = new ArrayList<>();
        permute(new Path(), new ArrayList<>(locations), routes, aman);
        return filterRoutes(routes,customerMap,restaurantMap);
    }

    /**
     * returns all permutations of paths
     */
    private static void permute(Path route, List<Location> remainingLocations, List<Path> routes, DeliveryPerson aman) {
        if (remainingLocations.isEmpty()) {
            if (route.getLocations().size() == remainingLocations.size() + 1
                    && route.getLocations().contains(aman.getDeliveryPersonLocation())) {
                routes.add(route);
            }
            routes.add(route);
            return;
        }
        for (int i = 0; i < remainingLocations.size(); i++) {
            Location location = remainingLocations.get(i);
            // Ensure the permutation starts with Aman's location
            if (ObjectUtils.isEmpty(route.getLocations()) || route.getLocations().get(0).equals(aman.getDeliveryPersonLocation())) {
                Path newRoute = new Path();
                List<Location> locations = new ArrayList<>();
                if( newRoute.getLocations() == null)newRoute.setLocations(locations);
                if(route.getLocations() != null) locations.addAll(route.getLocations());
                locations.add(location);
                newRoute.setLocations(locations);

                List<Location> newRemainingLocations = new ArrayList<>(remainingLocations);
                newRemainingLocations.remove(i);
                permute(newRoute, newRemainingLocations, routes, aman);
            }
        }
    }

    /**
     * returns list valid routes
     */
    public static List<Path> filterRoutes(List<Path> routes, Map<Location,Customer> customerMap,
                                          Map<Location,Restaurant> restaurantMap) {
        List<Path> filteredRoutes = new ArrayList<>();
        for (Path route : routes) {
            if (isValidRoute(route,customerMap,restaurantMap)) {
                filteredRoutes.add(route);
            }
        }
        return filteredRoutes;
    }

    /**
     *  Checks weather the route is valid or not
     *  <p>Scenarios Checked</p
     *      <li>Before delivering order to respective customer delivery person has taken order from restaurant or not</li>
     *      <li>Always we will start from delivery person</li>
     */
    private static boolean isValidRoute(Path route, Map<Location,Customer> customerMap, Map<Location,Restaurant> restaurantMap) {

        // Check if the delivery person visits the correct restaurant before delivering to the corresponding customer

        Set<String> visitedRestaurantIds = new HashSet<>();
        for (int i = 1; i < route.getLocations().size(); i++) {
            Location current = route.getLocations().get(i);
            Customer customer = customerMap.get(current);
            Restaurant restaurant = restaurantMap.get(current);
            if (RESTAURANT.equals(current.getLocationType())) {
                boolean isIdMatch =
                        customerMap.values().stream().anyMatch(c -> restaurant.getId().equals(c.getRestaurant().getId()));
                if(isIdMatch){
                    visitedRestaurantIds.add(restaurant.getId());
                }

            } else if (CUSTOMER.equals(current.getLocationType())) {
                if(!visitedRestaurantIds.contains(customer.getRestaurant().getId())){
                    return false;
                }
            }
        }

        return true;
    }
}
