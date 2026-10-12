
package com.skycampus.service;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;
import com.skycampus.model.EstadoMision;
import com.skycampus.model.Mision;
import com.skycampus.model.Prioridad;
import com.skycampus.model.TipoDrone;
import com.skycampus.strategy.EstrategiaAsignacion;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GestorMisionesEstrategiaPersonalizadaTest {

    @Test
    void debePermitirUsarUnaEstrategiaNuevaSinModificarGestorMisiones() {
        // Arrange: dos drones disponibles.
        Drone droneA = new Drone(
                "D001", TipoDrone.MINI, 50, true, EstadoDrone.DISPONIBLE
        );

        Drone droneB = new Drone(
                "D002", TipoDrone.CARGO, 90, true, EstadoDrone.DISPONIBLE
        );

        List<Drone> flota = List.of(droneA, droneB);

        Mision mision = new Mision(
                "M001",
                null,
                "Biblioteca ECI",
                400,
                Prioridad.NORMAL,
                EstadoMision.PENDIENTE,
                LocalDateTime.now()
        );

        // Una estrategia nueva: seleccionar el primer dron de la flota.
        // No es necesario modificar GestorMisiones para incorporarla.
        EstrategiaAsignacion estrategiaPersonalizada =
                new EstrategiaAsignacion() {
                    @Override
                    public Optional<Drone> seleccionar(
                            List<Drone> drones,
                            Mision m
                    ) {
                        return drones.stream().findFirst();
                    }
                };

        GestorMisiones gestor =
                new GestorMisiones(estrategiaPersonalizada);

        // Act: GestorMisiones delega la selección en la estrategia recibida.
        Optional<Drone> resultado = gestor.asignar(flota, mision);

        // Assert: se aplicó la estrategia personalizada.
        assertTrue(resultado.isPresent());
        assertEquals("D001", resultado.get().id());
        assertSame(droneA, resultado.get());
    }
}
