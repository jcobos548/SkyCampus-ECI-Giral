package model;

public record Drone(
        String id,
        int bateria,
        boolean disponible,
        String ubicacion
) {
}
