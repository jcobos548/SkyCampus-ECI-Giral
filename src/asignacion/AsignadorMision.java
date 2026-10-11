package asignacion;

import model.Drone;
import model.Mision;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AsignadorMision {

    private static final Logger LOGGER =
            Logger.getLogger(AsignadorMision.class.getName());

    public void asignarMision(Drone drone, Mision mision) {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.log(
                    Level.INFO,
                    "Asignando la misión al dron: {0}",
                    drone.id()
            );
        }
    }
}