package com.carwash.orderservice.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Car {
    private String carBrand;
    private String model;
    private String color;

}
