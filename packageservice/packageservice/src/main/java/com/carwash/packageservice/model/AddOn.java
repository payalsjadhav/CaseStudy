package com.carwash.packageservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="add-on")
public class AddOn {

    @Id
    private String addOnName;
    private String description;
    private double addOnPrice;
}
