
package com.skycampus.observer;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;
import com.skycampus.model.TipoDrone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorFlotaCuartoObservadorTest {

    @Test
    void debePermitirAgregarUnCuartoObservadorSinModificarGestorFlota() {
        GestorFlota gestor = new GestorFlota();

        // Suscribir los tres observadores existentes
        gestor.suscribir(new PanelOperador());
        gestor.suscribir(new SistemaLog());
        gestor.suscribir(new AlertaTecnico());

        // Cuarto observador: se define únicamente en la prueba
        class CuartoObservador implements ObservadorDrone {
            private boolean notificado = false;
            private EstadoDrone estadoRecibido;

            @Override
            public void onEstadoCambiado(
                    Drone drone, EstadoDrone nuevoEstado) {
                notificado = true;
                estadoRecibido = nuevoEstado;
            }
        }

        CuartoObservador cuarto = new CuartoObservador();
        gestor.suscribir(cuarto);

        Drone drone = new Drone(
                "D004",
                TipoDrone.MINI,
                80,
                true,
                EstadoDrone.DISPONIBLE
        );

        gestor.cambiarEstado(drone, EstadoDrone.FALLO);

        assertTrue(cuarto.notificado,
                "El cuarto observador debe recibir la notificación");
        assertEquals(EstadoDrone.FALLO, cuarto.estadoRecibido);
    }
}

