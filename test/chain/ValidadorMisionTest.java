
package chain;

import exceptions.DestinoInvalidoException;
import model.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorMisionTest {

    private ValidadorMision validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorMision();
    }

    // PRUEBAS DE BATERÍA

    @Test
    @DisplayName("Batería superior al 30% permite asignación")
    void bateriaSuperiorAlMinimo() {
        // Arrange
        Drone drone = new Drone(
                "D-01", 85, true, "Bloque A", 3.0
        );

        // Act
        boolean resultado = validador.tieneBateriaSuficiente(drone);

        // Assert
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Batería exactamente del 30% permite asignación")
    void bateriaExactaAlMinimo() {
        // Arrange
        Drone drone = new Drone(
                "D-02", 30, true, "Bloque B", 3.0
        );

        // Act
        boolean resultado = validador.tieneBateriaSuficiente(drone);

        // Assert
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Batería inferior al 30% impide asignación")
    void bateriaInferiorAlMinimo() {
        // Arrange
        Drone drone = new Drone(
                "D-04", 18, true, "Bloque B", 3.0
        );

        // Act
        boolean resultado = validador.tieneBateriaSuficiente(drone);

        // Assert
        assertFalse(resultado);
    }

    // PRUEBAS DE DESTINO

    @Test
    @DisplayName("Destino válido no lanza excepción")
    void destinoValido() {
        // Arrange
        String destino = "Biblioteca";

        // Act y Assert
        assertDoesNotThrow(
                () -> validador.validarDestino(destino)
        );
    }

    @Test
    @DisplayName("Destino inexistente lanza excepción")
    void destinoInexistente() {
        // Arrange
        String destino = "Edificio Inexistente";

        // Act y Assert
        assertThrows(
                DestinoInvalidoException.class,
                () -> validador.validarDestino(destino)
        );
    }

    @Test
    @DisplayName("Destino vacío lanza excepción")
    void destinoVacio() {
        // Arrange
        String destino = "";

        // Act y Assert
        assertThrows(
                DestinoInvalidoException.class,
                () -> validador.validarDestino(destino)
        );
    }

    // PRUEBAS DE DISPONIBILIDAD

    @Test
    @DisplayName("Dron disponible devuelve true")
    void droneDisponible() {
        // Arrange
        Drone drone = new Drone(
                "D-01", 85, true, "Bloque A", 3.0
        );

        // Act
        boolean resultado = validador.droneEstaDisponible(drone);

        // Assert
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Dron no disponible devuelve false")
    void droneNoDisponible() {
        // Arrange
        Drone drone = new Drone(
                "D-02", 42, false, "Biblioteca", 3.0
        );

        // Act
        boolean resultado = validador.droneEstaDisponible(drone);

        // Assert
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Dron no disponible con batería baja devuelve false")
    void droneNoDisponibleConBateriaBaja() {
        // Arrange
        Drone drone = new Drone(
                "D-04", 18, false, "Bloque B", 3.0
        );

        // Act
        boolean resultado = validador.droneEstaDisponible(drone);

        // Assert
        assertFalse(resultado);
    }
}
