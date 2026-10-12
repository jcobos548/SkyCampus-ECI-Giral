package com.skycampus.service;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;
import com.skycampus.model.EstadoMision;
import com.skycampus.model.Mision;
import com.skycampus.model.Prioridad;
import com.skycampus.model.TipoDrone;
import com.skycampus.observer.ObservadorDrone;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AsignadorMision {

    private static final int BATERIA_MINIMA = 20;

    private final ApiMeteorologica clima;
    private final ObservadorDrone observador;

    public AsignadorMision() {
        this(() -> true, (drone, estado) -> { });
    }

    public AsignadorMision(
            ApiMeteorologica clima,
            ObservadorDrone observador) {

        this.clima = clima;
        this.observador = observador;
    }

    /**
     * Asigna un dron teniendo en cuenta el clima y la prioridad.
     */
    public Optional<Drone> asignar(
            List<Drone> drones,
            Mision mision) {

        if (mision == null
                || drones == null
                || drones.isEmpty()
                || mision.estado() != EstadoMision.PENDIENTE
                || mision.pesoPaqueteGramos() <= 0) {
            return Optional.empty();
        }

        if (!clima.esApto()) {
            return Optional.empty();
        }

        List<Drone> aptos = drones.stream()
                .filter(Drone::disponible)
                .filter(d -> d.estado() == EstadoDrone.DISPONIBLE)
                .filter(d -> d.bateria() >= BATERIA_MINIMA)
                .filter(d -> puedeTransportar(
                        d.tipo(), mision.pesoPaqueteGramos()))
                .toList();

        Optional<Drone> seleccionado;

        if (mision.prioridad() == Prioridad.URGENTE) {
            seleccionado = aptos.stream()
                    .filter(d -> d.tipo() == TipoDrone.EXPRESS)
                    .max(Comparator.comparingInt(Drone::bateria));
        } else {
            seleccionado = aptos.stream()
                    .max(Comparator.comparingInt(Drone::bateria));
        }

        if (seleccionado.isEmpty()) {
            return Optional.empty();
        }

        Drone original = seleccionado.get();

        Drone asignado = new Drone(
                original.id(),
                original.tipo(),
                original.bateria(),
                false,
                EstadoDrone.EN_VUELO
        );

        observador.onEstadoCambiado(
                asignado, EstadoDrone.EN_VUELO);

        return Optional.of(asignado);
    }

    public Optional<Drone> asignarDrone(
            Mision mision,
            List<Drone> drones) {

        return asignar(drones, mision);
    }

    private boolean puedeTransportar(
            TipoDrone tipo,
            int pesoGramos) {

        return switch (tipo) {
            case MINI -> pesoGramos <= 500;
            case EXPRESS -> pesoGramos <= 2000;
            case CARGO -> pesoGramos <= 10000;
        };
    }

    public List<String> generarAlertas(List<Mision> misiones) {
        if (misiones == null || misiones.isEmpty()) {
            return List.of();
        }

        return misiones.stream()
                .filter(m -> m.prioridad() == Prioridad.URGENTE
                        && m.estado() == EstadoMision.PENDIENTE)
                .map(m -> "ALERTA: La misión " + m.id()
                        + " es urgente y está pendiente.")
                .toList();
    }
}
