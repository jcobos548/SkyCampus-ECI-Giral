
package retos;

import model.Drone;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reto1 {

    private static final Logger LOGGER =
            Logger.getLogger(Reto1.class.getName());

    public static void main(String[] args) {

        // Crear la flota de drones
        List<Drone> flota = List.of(
                new Drone("D-01", 85, true, "Bloque A", 3.0),
                new Drone("D-02", 42, false, "Biblioteca", 3.0),
                new Drone("D-03", 91, true, "Bloque C", 3.0),
                new Drone("D-04", 18, true, "Bloque B", 3.0),
                new Drone("D-05", 67, true, "Bloque D", 3.0)
        );

        LOGGER.info("Flota de SkyCampus creada correctamente.");
        LOGGER.log(
                Level.INFO,
                "Cantidad de drones: {0}",
                flota.size()
        );

        // CONSULTA 1: Drones disponibles con bateria >= 50%
        // Ordenados de mayor a menor bateria
        List<String> dronesDisponibles = flota.stream()
                .filter(Drone::disponible)
                .filter(drone -> drone.bateria() >= 50)
                .sorted((d1, d2) ->
                        Integer.compare(d2.bateria(), d1.bateria()))
                .map(Drone::id)
                .toList();

        LOGGER.log(Level.INFO, "Consulta 1: {0}", dronesDisponibles);

        // CONSULTA 2: Verificar si hay un dron disponible en el Bloque C
        boolean hayDronEnBloqueC = flota.stream()
                .anyMatch(drone ->
                        drone.disponible()
                                && drone.ubicacion().equals("Bloque C")
                );

        LOGGER.log(
                Level.INFO,
                "Consulta 2: {0}",
                hayDronEnBloqueC
        );

        // CONSULTA 3: Contar drones con bateria critica (< 20%)
        long dronesBateriaCritica = flota.stream()
                .filter(drone -> drone.bateria() < 20)
                .count();

        LOGGER.log(
                Level.INFO,
                "Consulta 3: {0}",
                dronesBateriaCritica
        );

        // CONSULTA 4: Transformar cada dron en un texto
        List<String> informacionDrones = flota.stream()
                .map(drone ->
                        drone.id() + ": " + drone.bateria() + "%")
                .toList();

        LOGGER.log(
                Level.INFO,
                "Consulta 4: {0}",
                informacionDrones
        );
    }
}
