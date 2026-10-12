package com.skycampus.model;

import java.time.LocalDateTime;

public record Mision(
        String id,
        Drone drone,
        String destino,
        int pesoPaqueteGramos,
        Prioridad prioridad,
        EstadoMision estado,
        LocalDateTime fechaCreacion
) {
}