
package retos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class Reto1Test {

    @Test
    void mainDebeEjecutarseSinErrores() {
        assertDoesNotThrow(() -> Reto1.main(new String[]{}));
    }

    @Test
    void mainDebeEjecutarseAunqueRecibaArgumentos() {
        assertDoesNotThrow(() -> Reto1.main(new String[]{"prueba"}));
    }
}
