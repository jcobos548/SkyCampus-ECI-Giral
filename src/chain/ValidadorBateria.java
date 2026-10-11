
package chain;

import model.Mision;

import java.util.logging.Logger;

public class ValidadorBateria extends Validador {

    private static final Logger LOGGER =
            Logger.getLogger(ValidadorBateria.class.getName());

    @Override
    protected boolean comprobar(Mision mision) {
        if (mision.drone().bateria() < 30) {
            LOGGER.warning("Rechazada: bateria inferior al 30%.");
            return false;
        }

        LOGGER.info("Bateria validada.");
        return true;
    }
}
