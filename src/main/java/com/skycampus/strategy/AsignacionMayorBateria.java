package com.skycampus.strategy;

import com.skycampus.model.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AsignacionMayorBateria implements EstrategiaAsignacion {

    @Override
    public Optional<Drone> seleccionar(List<Drone> flota, Mision mision) {
        if (flota == null || mision == null) {
            return Optional.empty();
        }

        return flota.stream()
                .filter(Drone::disponible)
                .filter(d -> d.estado() == EstadoDrone.DISPONIBLE)
                .filter(d -> capacidad(d.tipo()) >= mision.pesoPaqueteGramos())
                .max(Comparator.comparingInt(Drone::bateria));
    }

    private int capacidad(TipoDrone tipo) {
        return switch (tipo) {
            case MINI -> 500;
            case EXPRESS -> 2000;
            case CARGO -> 10000;
        };
    }
}