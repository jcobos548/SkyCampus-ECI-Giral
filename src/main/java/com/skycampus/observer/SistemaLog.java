package com.skycampus.observer;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;

public class SistemaLog implements ObservadorDrone {

    @Override
    public void onEstadoCambiado(Drone drone, EstadoDrone nuevoEstado) {
        System.out.println(
                "LOG: Drone " + drone.id() + ", estado: " + nuevoEstado);
    }
}
