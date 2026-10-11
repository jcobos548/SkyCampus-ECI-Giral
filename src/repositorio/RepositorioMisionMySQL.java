package repositorio;

import model.Mision;

public class RepositorioMisionMySQL
        implements RepositorioMision {

    @Override
    public void guardar(Mision mision) {
        // Aquí va la lógica JDBC para guardar la misión.
        // La conexión se configura fuera de esta clase
        // o se inyecta mediante un componente de conexión.
        System.out.println("Guardando misión en MySQL.");
    }
}