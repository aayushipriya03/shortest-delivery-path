package com.deliverysystem.service;

import com.deliverysystem.dto.RequestDto;
import com.deliverysystem.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OptimizedPathFinder {

    public double minimumTimeCalculator(RequestDto requestDto) {
        Map<Location,Customer> customerMap =  getLocationToCustomerMap(requestDto.getCustomers());
        Map<Location,Restaurant> restaurantMap = getLocationToRestaurantMap(requestDto.getRestaurants());


        List<Location> locations = new ArrayList<>();

        List<DeliveryPerson> deliveryPersonList = requestDto.getDeliveryPerson();
        List<Restaurant> restaurantList = requestDto.getRestaurants();
        List<Customer> customerList = requestDto.getCustomers();

        deliveryPersonList.forEach(deliveryPerson -> locations.add(deliveryPerson.getDeliveryPersonLocation()));
        restaurantList.forEach(restaurant -> locations.add(restaurant.getRestaurantLocation()));
        customerList.forEach(customer -> locations.add(customer.getCustomerLocation()));

        // Generate all possible routes
        List<Path> routes = PathGenerator.generateRoutes(locations, deliveryPersonList.get(0),customerMap,restaurantMap);

        double preparationTime1 = 0.8;
        double preparationTime2 = 0.9;

        double shortestTime = TimeCalculator.shortestDeliveryTime(routes, preparationTime1, preparationTime2);
        return shortestTime;
    }

    /**
     * returns Map of Location to Customer
     */
    public Map<Location,Customer> getLocationToCustomerMap(List<Customer> customerList){
        Map<Location,Customer> customerMap = new HashMap<>();

        customerList.forEach(customer -> {
            customerMap.put(customer.getCustomerLocation(),customer);
        });
        return customerMap;
    }

    /**
     * returns Map of Location to Restaurant
     */
    public Map<Location,Restaurant> getLocationToRestaurantMap(List<Restaurant> restaurantList){
        Map<Location,Restaurant> restaurantMap = new HashMap<>();

        restaurantList.forEach(restaurant -> {
            restaurantMap.put(restaurant.getRestaurantLocation(),restaurant);
        });
        return restaurantMap;
    }
}
