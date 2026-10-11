package asignacion;

import model.Drone;
import model.Mision;

public class AsignadorMision {

    public void asignarMision(Drone drone, Mision mision) {
        // Aquí va la lógica de asignación.
        System.out.println(
                "Asignando la misión al dron: " + drone.id()
        );
    }
}