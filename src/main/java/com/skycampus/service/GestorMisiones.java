package com.skycampus.service;

import com.skycampus.model.Drone;
import com.skycampus.model.Mision;
import com.skycampus.strategy.EstrategiaAsignacion;

import java.util.List;
import java.util.Optional;

public class GestorMisiones {

    private EstrategiaAsignacion estrategia;

    public GestorMisiones(EstrategiaAsignacion estrategia) {
        if (estrategia == null) {
            throw new IllegalArgumentException("La estrategia no puede ser nula");
        }
        this.estrategia = estrategia;
    }

    public void cambiarEstrategia(EstrategiaAsignacion estrategia) {
        if (estrategia == null) {
            throw new IllegalArgumentException("La estrategia no puede ser nula");
        }
        this.estrategia = estrategia;
    }

    public Optional<Drone> asignar(List<Drone> flota, Mision mision) {
        return estrategia.seleccionar(flota, mision);
    }
}