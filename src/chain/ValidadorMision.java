
package chain;

import exceptions.DestinoInvalidoException;
import model.Drone;

import java.util.Set;

public class ValidadorMision {

    private static final int BATERIA_MINIMA = 30;

    private static final Set<String> DESTINOS_VALIDOS = Set.of(
            "Biblioteca",
            "Bloque A",
            "Bloque B",
            "Bloque C",
            "Bloque D"
    );

    public boolean tieneBateriaSuficiente(Drone drone) {
        return drone.bateria() >= BATERIA_MINIMA;
    }

    public void validarDestino(String destino) {
        if (destino == null || !DESTINOS_VALIDOS.contains(destino.trim())) {
            throw new DestinoInvalidoException(
                    "El destino no es válido: " + destino
            );
        }
    }

    public boolean droneEstaDisponible(Drone drone) {
        return drone.disponible();
    }
}
