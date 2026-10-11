
package strategy;

import model.Drone;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstrategiaMayorBateriaTest {

    private final EstrategiaMayorBateria estrategia =
            new EstrategiaMayorBateria();

    @Test
    void debeSeleccionarElDronDisponibleConMayorBateria() {
        Drone dron1 = new Drone("D-01", 50, true, "Bloque A", 3.0);
        Drone dron2 = new Drone("D-02", 90, true, "Bloque B", 3.0);
        Drone dron3 = new Drone("D-03", 70, true, "Bloque C", 3.0);

        Drone resultado = estrategia.seleccionarDron(
                List.of(dron1, dron2, dron3)
        );

        assertEquals(dron2, resultado);
    }

    @Test
    void noDebeSeleccionarDronesNoDisponibles() {
        Drone dron1 = new Drone("D-01", 95, false, "Bloque A", 3.0);
        Drone dron2 = new Drone("D-02", 70, true, "Bloque B", 3.0);

        Drone resultado = estrategia.seleccionarDron(
                List.of(dron1, dron2)
        );

        assertEquals(dron2, resultado);
    }

    @Test
    void debeRetornarNullSiNingunDronEstaDisponible() {
        Drone dron1 = new Drone("D-01", 95, false, "Bloque A", 3.0);
        Drone dron2 = new Drone("D-02", 70, false, "Bloque B", 3.0);

        Drone resultado = estrategia.seleccionarDron(
                List.of(dron1, dron2)
        );

        assertNull(resultado);
    }

    @Test
    void debeRetornarNullSiLaListaEstaVacia() {
        Drone resultado = estrategia.seleccionarDron(List.of());

        assertNull(resultado);
    }

    @Test
    void debeIgnorarElementosNullDeLaLista() {
        Drone dron = new Drone("D-01", 80, true, "Bloque A", 3.0);

        Drone resultado = estrategia.seleccionarDron(
                Arrays.asList(null, dron, null)
        );

        assertEquals(dron, resultado);
    }

    @Test
    void debeLanzarExcepcionSiLaListaEsNull() {
        assertThrows(
                NullPointerException.class,
                () -> estrategia.seleccionarDron(null)
        );
    }
}
