package com.skycampus.observer;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;

public class AlertaTecnico implements ObservadorDrone {

    @Override
    public void onEstadoCambiado(Drone drone, EstadoDrone nuevoEstado) {
        if (nuevoEstado == EstadoDrone.FALLO) {
            System.out.println(
                    "ALERTA TÉCNICA: revisar drone " + drone.id());
        }
    }
}
