# Optimized Delivery Path

## Overview

The Optimized Delivery Path is a Spring Boot application designed to help delivery executives optimize their routes for delivering orders in the shortest possible time. It calculates the optimal route based on the provided locations of the delivery boy, restaurants, and destinations.
## Flow

1. **Data Initialization**:
    - `DeliverySystemController` is the controller class, which will handle the input for the problem statement. Request body should adhere to following format.
    - endpoint for testing: http://localhost:8080/shortest-delivery-path
    - locations given inside restaurant array should be correctly mapped in location for customers array 
    -  ```json
         {
             "deliveryPerson": [
                 {
                     "id": "d1",
                     "name": "abc1",
                     "deliveryPersonLocation": {
                         "latitude": 10.0,
                         "longitude": 44.0,
                         "locationType": "DELIVERY_PERSON"
                     }
                 }
             ],
             "restaurants": [
                 {
                     "id": "r1",
                     "name": "abc1",
                     "restaurantLocation": {
                         "latitude": 15.0,
                         "longitude": 16.0,
                         "locationType": "RESTAURANT"
                     }
                 },
                 {
                     "id": "r2",
                     "name": "abc2",
                     "restaurantLocation": {
                         "latitude": 17.0,
                         "longitude": 18.0,
                         "locationType": "RESTAURANT"
                     }
                 }
             ],
             "customers": [
                 {
                     "id": "c1",
                     "name": "abc3",
                     "customerLocation": {
                         "latitude": 5.0,
                         "longitude": 8.0,
                         "locationType": "CUSTOMER"
                     },
                     "restaurant": {
                         "id": "r1",
                         "name": "abc1",
                         "restaurantLocation": {
                             "latitude": 15.0,
                             "longitude": 16.0,
                             "locationType": "RESTAURANT"
                         }
                     }
                 },
                 {
                     "id": "c2",
                     "name": "abc4",
                     "customerLocation": {
                         "latitude": 11.0,
                         "longitude": 14.0,
                         "locationType": "CUSTOMER"
                     },
                     "restaurant": {
                         "id": "r2",
                         "name": "abc2",
                         "restaurantLocation": {
                             "latitude": 17.0,
                             "longitude": 18.0,
                             "locationType": "RESTAURANT"
                         }
                     }
                 }
             ]
         }

2. **Path Generation**:
    - The `PathGenerator` class generates all possible routes starting from the delivery person's location, passing through restaurants, and ending at customers' locations.

3. **Path Filtering**:
    - The generated routes are filtered to ensure that each route starts from the delivery person's location, visits the correct restaurant before delivering to the corresponding customer, and follows a valid sequence of locations.
    - before delivering at a particular destination d1, DE should have came across certain restaurant r1

4. **Shortest Delivery Time Calculation**:
    - The `TimeCalculator` class calculates the shortest delivery time among the filtered routes, taking into account travel time between locations and food preparation time at restaurants.

5. **Output**:
    - The shortest delivery time is displayed as output, providing an estimate of the time required to deliver the batch of orders.
    - Sample Response Output
    - ```json
      {
        "minimum time": 234.4793043490521
      }

## Features

- Automatic generation of delivery routes.
- Calculation of the shortest delivery time.
- Customizable food preparation time at restaurants.
- Efficient data management using maps.
- Object-oriented design for modularity and extensibility.

## Assumptions

- Each location on the map can have either one restaurant or one customer. There are no locations with both a restaurant and a customer simultaneously.
- The delivery person only goes to the customer's location to deliver the order after picking it up from the restaurant. There are no direct deliveries from the restaurant to the customer without the involvement of the delivery person.

## Usage

1. **Prerequisites**:
    - Java Development Kit (JDK) installed on your system.
    - Maven build tool installed.
   ``` bash
       sudo apt install maven
   ```

2. **Setup**:
    - Clone the project repository to your local machine.
    - Import the project into your preferred IDE.

3. **Execution**:
    - Run the `DeliverySystemApplication` main class to start the server

4. **Configuration**:
   - Call the endpoint with below curl structure
   - ```json
     curl --location 'http://localhost:8080/shortest-delivery-path' \
      --header 'Content-Type: application/json' \
      --data '{
      "deliveryPerson": [
      {
      "id": "d1",
      "name": "abc1",
      "deliveryPersonLocation": {
      "latitude": 10.0,
      "longitude": 44.0,
      "locationType": "DELIVERY_PERSON"
      }
      }
      ],
      "restaurants": [
      {
      "id": "r1",
      "name": "abc1",
      "restaurantLocation": {
      "latitude": 15.0,
      "longitude": 16.0,
      "locationType": "RESTAURANT"
      }
      },
      {
      "id": "r2",
      "name": "abc2",
      "restaurantLocation": {
      "latitude": 17.0,
      "longitude": 18.0,
      "locationType": "RESTAURANT"
      }
      }
      ],
      "customers": [
      {
      "id": "c1",
      "name": "abc3",
      "customerLocation": {
      "latitude": 5.0,
      "longitude": 8.0,
      "locationType": "CUSTOMER"
      },
      "restaurant": {
      "id": "r1",
      "name": "abc1",
      "restaurantLocation": {
      "latitude": 15.0,
      "longitude": 16.0,
      "locationType": "RESTAURANT"
      }
      }
      },
      {
      "id": "c2",
      "name": "abc4",
      "customerLocation": {
      "latitude": 11.0,
      "longitude": 14.0,
      "locationType": "CUSTOMER"
      },
      "restaurant": {
      "id": "r2",
      "name": "abc2",
      "restaurantLocation": {
      "latitude": 17.0,
      "longitude": 18.0,
      "locationType": "RESTAURANT"
      }
      }
      }
      ]
      }'

5. **Testing**:
    - Run the unit tests in selected IDE.

6. **Customization**:
    - Adjust the food preparation time (`preparationTime1` and `preparationTime2` variables) in the `OptimizedPathFinder` class to reflect actual preparation times at restaurants.