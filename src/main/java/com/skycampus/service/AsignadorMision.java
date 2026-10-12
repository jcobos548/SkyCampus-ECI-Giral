package com.skycampus.service;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;
import com.skycampus.model.EstadoMision;
import com.skycampus.model.Mision;
import com.skycampus.model.TipoDrone;

import java.util.List;
import java.util.Optional;

public class AsignadorMision {

    private static final int BATERIA_MINIMA = 20;

    public Optional<Drone> asignarDrone(Mision mision, List<Drone> drones) {
        if (mision == null || drones == null || drones.isEmpty()) {
            return Optional.empty();
        }

        if (mision.estado() != EstadoMision.PENDIENTE) {
            return Optional.empty();
        }

        return drones.stream()
                .filter(Drone::disponible)
                .filter(drone -> drone.estado() == EstadoDrone.DISPONIBLE)
                .filter(drone -> drone.bateria() >= BATERIA_MINIMA)
                .filter(drone -> puedeTransportar(drone.tipo(),
                        mision.pesoPaqueteGramos()))
                .findFirst();
    }

    private boolean puedeTransportar(TipoDrone tipo, int pesoGramos) {
        return switch (tipo) {
            case MINI -> pesoGramos <= 500;
            case EXPRESS -> pesoGramos <= 2000;
            case CARGO -> pesoGramos <= 10000;
        };
    }
}
