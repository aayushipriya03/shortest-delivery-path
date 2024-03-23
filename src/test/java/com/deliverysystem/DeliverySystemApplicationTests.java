package com.deliverysystem;

import com.deliverysystem.enums.LocationType;
import com.deliverysystem.model.*;
import com.deliverysystem.service.OptimizedPathFinder;
import com.deliverysystem.service.PathGenerator;
import com.deliverysystem.service.TimeCalculator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.RouteMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DeliverySystemApplicationTests {
	@Mock
	TimeCalculator timeCalculator ;

	@Mock
	OptimizedPathFinder optimizedPathFinder;

	@Mock
	PathGenerator pathGenerator;

	@Test
	void contextLoads() {
	}
	@Test
	public void testShortestDeliveryTimeCalculation() {
		List<Path> routes = new ArrayList<>();


		double pt1 = 0.5;
		double pt2 = 0.7;

		// When
		double shortestTime = timeCalculator.shortestDeliveryTime(routes, pt1, pt2);

		// Then
		assertTrue(shortestTime >= 0); // Ensure the shortest time is non-negative
	}


	@Test
	public void testRouteGeneration() {
		// Given
		List<Location> locationList = new ArrayList<>();

		List<DeliveryPerson> deliveryPersons = new ArrayList<>();
		List<Restaurant> restaurants = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();

		DeliveryPerson aman = new DeliveryPerson("D1","Aman","aman@gmail.com","8765432109",
				new Location(12.9304, 77.6209,LocationType.DELIVERY_PERSON));

		deliveryPersons.add(aman);

		Restaurant restaurant1 = new Restaurant("R1", "abc1",
				new Location(12.9345, 77.62543, LocationType.RESTAURANT));

		Restaurant restaurant2 = new Restaurant("R2", "abc2",
				new Location(12.9345, 77.6254, LocationType.RESTAURANT ));

		restaurants.add(restaurant1);
		restaurants.add(restaurant2);

		Customer customer1 = new Customer("C1", "Aayushi", "Ay@gmail.com", "8978675432",
				new Location(12.9581, 77.71108,LocationType.CUSTOMER), restaurants.get(0));

		Customer customer2 = new Customer("C2", "Priya", "Pr@gmail.com", "8978675433",
				new Location(12.9081, 77.6476, LocationType.CUSTOMER), restaurants.get(1));

		customers.add(customer1);
		customers.add(customer2);

		deliveryPersons.forEach(deliveryPerson -> locationList.add(deliveryPerson.getDeliveryPersonLocation()));
		restaurants.forEach(restaurant -> locationList.add(restaurant.getRestaurantLocation()));
		customers.forEach(customer -> locationList.add(customer.getCustomerLocation()));

		assertTrue(locationList.size() > 0);

		Map<Location, Customer> customerMap = optimizedPathFinder.getLocationToCustomerMap(customers);
		Map<Location, Restaurant> restaurantMap = optimizedPathFinder.getLocationToRestaurantMap(restaurants);




	}
}
