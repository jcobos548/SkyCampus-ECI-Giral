package com.skycampus.observer;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;

public class PanelOperador implements ObservadorDrone {

    @Override
    public void onEstadoCambiado(Drone drone, EstadoDrone nuevoEstado) {
        System.out.println(
                "Panel operador: " + drone.id() + " -> " + nuevoEstado);
    }
}
