package com.autodiler.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CarStatus {
    WAITING("Ожидание"),
    ACTIVE("Активно"),
    ON_REGISTRATION("На оформлении"),
    SOLD("Продано"),
    REFUSED("Отказано");
    private final String name;
}

