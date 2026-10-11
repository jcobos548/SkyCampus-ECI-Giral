
package notificacion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AlertaOperadorEmailTest {

    @Test
    void enviarDebeEjecutarseSinErrores() {
        AlertaOperadorEmail alerta = new AlertaOperadorEmail();

        assertDoesNotThrow(
                () -> alerta.enviar(
                        "operador@skycampus.edu.co",
                        "Misión asignada"
                )
        );
    }

    @Test
    void enviarDebeAceptarMensajeVacio() {
        AlertaOperadorEmail alerta = new AlertaOperadorEmail();

        assertDoesNotThrow(
                () -> alerta.enviar("operador@skycampus.edu.co", "")
        );
    }
}
