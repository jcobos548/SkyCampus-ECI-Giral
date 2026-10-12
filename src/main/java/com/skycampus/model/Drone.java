package com.skycampus.model;

public record Drone(
        String id,
        TipoDrone tipo,
        int bateria,
        boolean disponible,
        EstadoDrone estado
) {
}
