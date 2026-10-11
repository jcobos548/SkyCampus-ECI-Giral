package asignacion;

import model.Drone;
import model.Mision;
import model.TipoCarga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AsignadorMisionTest {

    @Test
    void asignarMisionDebeEjecutarseSinErrores() {
        Drone drone = new Drone(
                "D-01",
                85,
                true,
                "Bloque A",
                3.0
        );

        Mision mision = new Mision(
                drone,
                "Bloque A",
                "Biblioteca",
                "Entrega de prueba",
                1,
                TipoCarga.PAQUETE_PEQUENO
        );

        AsignadorMision asignador = new AsignadorMision();

        assertDoesNotThrow(
                () -> asignador.asignarMision(drone, mision)
        );
    }
}
