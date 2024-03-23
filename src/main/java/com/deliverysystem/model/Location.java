package com.deliverysystem.model;


import com.deliverysystem.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Location {
    double latitude;
    double longitude;
    LocationType locationType;
}
