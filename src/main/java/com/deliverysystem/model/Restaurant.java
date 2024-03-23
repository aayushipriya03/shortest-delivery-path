package com.deliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {
    String id;
    String name;
    Location restaurantLocation;
}
