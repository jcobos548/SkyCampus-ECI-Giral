
package chain;

import model.Mision;

import java.util.List;
import java.util.logging.Logger;

public class ValidadorDestino extends Validador {

    private static final Logger LOGGER =
            Logger.getLogger(ValidadorDestino.class.getName());

    private final List<String> destinosValidos;

    public ValidadorDestino(List<String> destinosValidos) {
        this.destinosValidos = List.copyOf(destinosValidos);
    }

    @Override
    protected boolean comprobar(Mision mision) {
        if (!destinosValidos.contains(mision.destino())) {
            LOGGER.warning("Rechazada: el destino no existe.");
            return false;
        }

        LOGGER.info("Destino validado.");
        return true;
    }
}
