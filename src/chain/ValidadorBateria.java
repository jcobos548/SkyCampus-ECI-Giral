package chain;

import model.Mision;

public class ValidadorBateria extends Validador {

    @Override
    protected boolean comprobar(Mision mision) {
        if (mision.drone().bateria() < 30) {
            System.out.println(
                    "Rechazada: bateria inferior al 30%."
            );
            return false;
        }

        System.out.println("Bateria validada.");
        return true;
    }
}