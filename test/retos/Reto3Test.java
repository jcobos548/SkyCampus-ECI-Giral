package retos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class Reto3Test {

    @Test
    void mainDebeEjecutarseSinErrores() {
        assertDoesNotThrow(() -> Reto3.main(new String[]{}));
    }

    @Test
    void mainDebeEjecutarseAunqueRecibaArgumentos() {
        assertDoesNotThrow(() -> Reto3.main(new String[]{"prueba"}));
    }
}
