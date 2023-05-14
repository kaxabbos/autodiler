package com.autodiler.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BodyType {
    MINIVAN("Минивен"),
    OFF_ROAD_VEHICLE("Внедорожник"),
    COUPE("Купе"),
    SEDAN("Седан"),
    UNIVERSAL("Универсал"),
    HATCHBACK("Хэтчбек");
    private final String name;
}

