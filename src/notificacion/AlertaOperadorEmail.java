
package notificacion;

import java.util.logging.Logger;

public class AlertaOperadorEmail implements AlertaOperador {

    private static final Logger LOGGER =
            Logger.getLogger(AlertaOperadorEmail.class.getName());

    @Override
    public void enviar(String operador, String mensaje) {
        // Aquí va la lógica SMTP.
        LOGGER.log(
                java.util.logging.Level.INFO,
                "Enviando correo a {0}: {1}",
                new Object[]{operador, mensaje}
        );
    }
}

