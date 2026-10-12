package com.skycampus.strategy;

import com.skycampus.model.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AsignacionMenorUso implements EstrategiaAsignacion {

    private final Map<String, Integer> usos = new HashMap<>();

    public void registrarUso(Drone drone) {
        if (drone != null) {
            usos.merge(drone.id(), 1, Integer::sum);
        }
    }

    @Override
    public Optional<Drone> seleccionar(List<Drone> flota, Mision mision) {
        if (flota == null || mision == null) {
            return Optional.empty();
        }

        return flota.stream()
                .filter(Drone::disponible)
                .filter(d -> d.estado() == EstadoDrone.DISPONIBLE)
                .filter(d -> capacidad(d.tipo()) >= mision.pesoPaqueteGramos())
                .min(Comparator.comparingInt(
                        d -> usos.getOrDefault(d.id(), 0)));
    }

    private int capacidad(TipoDrone tipo) {
        return switch (tipo) {
            case MINI -> 500;
            case EXPRESS -> 2000;
            case CARGO -> 10000;
        };
    }
}