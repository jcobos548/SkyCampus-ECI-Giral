
package com.skycampus.observer;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;
import com.skycampus.model.TipoDrone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorFlotaTest {

    @Test
    void debeNotificarCuandoCambiaElEstadoDelDrone() {
        GestorFlota gestor = new GestorFlota();

        Drone drone = new Drone(
                "D001",
                TipoDrone.MINI,
                80,
                true,
                EstadoDrone.DISPONIBLE
        );

        Drone droneActualizado =
                gestor.cambiarEstado(drone, EstadoDrone.FALLO);

        assertEquals(EstadoDrone.FALLO, droneActualizado.estado());
        assertFalse(droneActualizado.disponible());
    }

    @Test
    void debePermitirSuscribirObservadores() {
        GestorFlota gestor = new GestorFlota();

        assertDoesNotThrow(() -> {
            gestor.suscribir(new PanelOperador());
            gestor.suscribir(new SistemaLog());
            gestor.suscribir(new AlertaTecnico());
        });
    }
}
