
package repositorio;

import model.Drone;
import model.Mision;
import model.TipoCarga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RepositorioMisionMySQLTest {

    @Test
    void guardarDebeEjecutarseSinErrores() {
        Drone drone = new Drone(
                "D-01", 85, true, "Bloque A", 3.0
        );

        Mision mision = new Mision(
                drone,
                "Bloque A",
                "Biblioteca",
                "Entrega de prueba",
                1,
                TipoCarga.PAQUETE_PEQUENO
        );

        RepositorioMision repositorio = new RepositorioMisionMySQL();

        assertDoesNotThrow(() -> repositorio.guardar(mision));
    }
}
