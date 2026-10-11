package chain;

import model.Mision;

import java.util.List;

public class ValidadorDestino extends Validador {

    private final List<String> destinosValidos;

    public ValidadorDestino(List<String> destinosValidos) {
        this.destinosValidos = List.copyOf(destinosValidos);
    }

    @Override
    protected boolean comprobar(Mision mision) {
        if (!destinosValidos.contains(mision.destino())) {
            System.out.println(
                    "Rechazada: el destino no existe."
            );
            return false;
        }

        System.out.println("Destino validado.");
        return true;
    }
}
