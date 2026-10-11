package strategy;

import model.Drone;

import java.util.List;

public interface EstrategiaAsignacion {

    Drone seleccionarDron(List<Drone> drones);
}
