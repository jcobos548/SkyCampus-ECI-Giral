
package repositorio;

import model.Mision;

import java.util.logging.Logger;

public class RepositorioMisionMySQL implements RepositorioMision {

    private static final Logger LOGGER =
            Logger.getLogger(RepositorioMisionMySQL.class.getName());

    @Override
    public void guardar(Mision mision) {
        // Aquí va la lógica JDBC para guardar la misión.
        // La conexión se configura fuera de esta clase
        // o se inyecta mediante un componente de conexión.
        LOGGER.info("Guardando misión en MySQL.");
    }
}
