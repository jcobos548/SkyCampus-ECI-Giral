
package com.skycampus.service;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;
import com.skycampus.model.EstadoMision;
import com.skycampus.model.Mision;
import com.skycampus.model.Prioridad;
import com.skycampus.model.TipoDrone;
import com.skycampus.strategy.AsignacionMayorBateria;
import com.skycampus.strategy.AsignacionTipoCompatible;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GestorMisionesTest {

    @Test
    void debePermitirCambiarLaEstrategiaDeAsignacion() {
        Drone mini = new Drone(
                "D001", TipoDrone.MINI, 30, true,
                EstadoDrone.DISPONIBLE
        );

        Drone cargo = new Drone(
                "D002", TipoDrone.CARGO, 90, true,
                EstadoDrone.DISPONIBLE
        );

        Mision mision = new Mision(
                "M001", null, "Bogotá", 400,
                Prioridad.NORMAL, EstadoMision.PENDIENTE,
                LocalDateTime.now()
        );

        GestorMisiones gestor =
                new GestorMisiones(new AsignacionMayorBateria());

        List<Drone> flota = List.of(mini, cargo);

        Optional<Drone> primeraAsignacion =
                gestor.asignar(flota, mision);

        assertTrue(primeraAsignacion.isPresent());
        assertEquals("D002", primeraAsignacion.get().id());

        gestor.cambiarEstrategia(new AsignacionTipoCompatible());

        Optional<Drone> segundaAsignacion =
                gestor.asignar(flota, mision);

        assertTrue(segundaAsignacion.isPresent());
        assertEquals("D001", segundaAsignacion.get().id());
    }
}
