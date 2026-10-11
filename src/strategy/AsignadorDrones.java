package strategy;

import model.Drone;

import java.util.List;
import java.util.Objects;

public class AsignadorDrones {

    private final EstrategiaAsignacion estrategia;

    public AsignadorDrones(EstrategiaAsignacion estrategia) {
        this.estrategia = Objects.requireNonNull(
                estrategia,
                "La estrategia de asignación no puede ser null"
        );
    }

    public Drone asignar(List<Drone> drones) {
        return estrategia.seleccionarDron(drones);
    }
}