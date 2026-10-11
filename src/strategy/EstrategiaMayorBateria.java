package strategy;

import model.Drone;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class EstrategiaMayorBateria implements EstrategiaAsignacion {

    @Override
    public Drone seleccionarDron(List<Drone> drones) {
        Objects.requireNonNull(drones, "La lista de drones no puede ser null");

        return drones.stream()
                .filter(Objects::nonNull)
                .filter(Drone::disponible)
                .max(Comparator.comparingInt(Drone::bateria))
                .orElse(null);
    }
}