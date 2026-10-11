
package strategy;

import model.Drone;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AsignadorDronesTest {

    @Test
    void debeAsignarElDronSeleccionadoPorLaEstrategia() {
        Drone dron1 = new Drone("D-01", 60, true, "Bloque A", 3.0);
        Drone dron2 = new Drone("D-02", 90, true, "Bloque B", 3.0);

        AsignadorDrones asignador =
                new AsignadorDrones(new EstrategiaMayorBateria());

        Drone resultado = asignador.asignar(List.of(dron1, dron2));

        assertEquals(dron2, resultado);
    }

    @Test
    void debeRetornarNullSiNoHayDronesDisponibles() {
        Drone dron = new Drone("D-01", 90, false, "Bloque A", 3.0);

        AsignadorDrones asignador =
                new AsignadorDrones(new EstrategiaMayorBateria());

        Drone resultado = asignador.asignar(List.of(dron));

        assertNull(resultado);
    }

    @Test
    void debeRechazarUnaEstrategiaNull() {
        assertThrows(
                NullPointerException.class,
                () -> new AsignadorDrones(null)
        );
    }
}
