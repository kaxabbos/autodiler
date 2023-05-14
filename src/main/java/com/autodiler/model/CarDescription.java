package com.autodiler.model;

import com.autodiler.model.enums.BodyType;
import com.autodiler.model.enums.Fuel;
import com.autodiler.model.enums.Transmission;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarDescription {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
    @Enumerated(EnumType.STRING)
    private Fuel fuel;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Column(length = 5000)
    private String description;
    private int year;
    private int mileage;
    private String origin;
    private String citySale;
    private String brand;

    public CarDescription(BodyType bodyType, Fuel fuel, Transmission transmission, String description, int year, int mileage, String origin, String citySale, String brand) {
        this.bodyType = bodyType;
        this.fuel = fuel;
        this.transmission = transmission;
        this.description = description;
        this.year = year;
        this.mileage = mileage;
        this.origin = origin;
        this.citySale = citySale;
        this.brand = brand;
    }
}
