package com.skycampus.observer;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;

import java.util.ArrayList;
import java.util.List;

public class GestorFlota {

    private final List<ObservadorDrone> observadores = new ArrayList<>();

    public void suscribir(ObservadorDrone observador) {
        if (observador != null) {
            observadores.add(observador);
        }
    }

    public Drone cambiarEstado(Drone drone, EstadoDrone nuevoEstado) {
        if (drone == null || nuevoEstado == null) {
            throw new IllegalArgumentException("Drone y estado son obligatorios");
        }

        Drone actualizado = new Drone(
                drone.id(),
                drone.tipo(),
                drone.bateria(),
                nuevoEstado == EstadoDrone.DISPONIBLE,
                nuevoEstado
        );

        observadores.forEach(
                observador -> observador.onEstadoCambiado(actualizado, nuevoEstado)
        );

        return actualizado;
    }
}
