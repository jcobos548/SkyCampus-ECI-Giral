
package chain;

import model.Mision;

import java.util.logging.Logger;

public class ValidadorCarga extends Validador {

    private static final Logger LOGGER =
            Logger.getLogger(ValidadorCarga.class.getName());

    @Override
    protected boolean comprobar(Mision mision) {
        double pesoCarga = mision.tipoCarga().pesoKg();
        double capacidad = mision.drone().capacidadCargaKg();

        if (pesoCarga > capacidad) {
            LOGGER.warning(
                    "Rechazada: la carga supera la capacidad del drone."
            );
            return false;
        }

        LOGGER.info("Carga validada.");
        return true;
    }
}
