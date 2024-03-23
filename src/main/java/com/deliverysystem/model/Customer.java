package com.deliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
    String id;
    String name;
    String email;
    String phoneNumber;
    Location customerLocation;

    Restaurant restaurant;
}
