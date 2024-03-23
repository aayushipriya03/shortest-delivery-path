package com.deliverysystem.dto;

import com.deliverysystem.model.Customer;
import com.deliverysystem.model.DeliveryPerson;
import com.deliverysystem.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RequestDto {
    private List<Customer> customers;
    private List<Restaurant> restaurants;
    private List<DeliveryPerson> deliveryPerson;
}
