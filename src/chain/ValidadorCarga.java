package chain;

import model.Mision;

public class ValidadorCarga extends Validador {

    @Override
    protected boolean comprobar(Mision mision) {
        double pesoCarga = mision.tipoCarga().pesoKg();
        double capacidad = mision.drone().capacidadCargaKg();

        if (pesoCarga > capacidad) {
            System.out.println(
                    "Rechazada: la carga supera la capacidad del drone."
            );
            return false;
        }

        System.out.println("Carga validada.");
        return true;
    }
}