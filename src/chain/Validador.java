package chain;

import model.Mision;

public abstract class Validador {

    private Validador siguiente;

    public Validador conectarCon(Validador siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    public boolean validar(Mision mision) {
        if (!comprobar(mision)) {
            return false;
        }

        if (siguiente == null) {
            return true;
        }

        return siguiente.validar(mision);
    }

    protected abstract boolean comprobar(Mision mision);
}
