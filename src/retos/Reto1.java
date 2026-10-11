package retos;

import model.Drone;
import java.util.List;

public class Reto1 {

    public static void main(String[] args) {

        // Crear la flota de drones
        List<Drone> flota = List.of(
                new Drone("D-01", 85, true, "Bloque A", 3.0),
                new Drone("D-02", 42, false, "Biblioteca", 3.0),
                new Drone("D-03", 91, true, "Bloque C", 3.0),
                new Drone("D-04", 18, true, "Bloque B", 3.0),
                new Drone("D-05", 67, true, "Bloque D", 3.0)
        );

        System.out.println("Flota de SkyCampus creada correctamente.");
        System.out.println("Cantidad de drones: " + flota.size());

        // CONSULTA 1: Drones disponibles con bateria >= 50%
        // Ordenados de mayor a menor bateria
        List<String> dronesDisponibles = flota.stream()
                .filter(Drone::disponible)
                .filter(drone -> drone.bateria() >= 50)
                .sorted((d1, d2) ->
                        Integer.compare(d2.bateria(), d1.bateria()))
                .map(Drone::id)
                .toList();

        System.out.println("Consulta 1: " + dronesDisponibles);

        // CONSULTA 2: Verificar si hay un dron disponible en el Bloque C
        boolean hayDronEnBloqueC = flota.stream()
                .anyMatch(drone ->
                        drone.disponible()
                                && drone.ubicacion().equals("Bloque C")
                );

        System.out.println("Consulta 2: " + hayDronEnBloqueC);

        // CONSULTA 3: Contar drones con bateria critica (< 20%)
        long dronesBateriaCritica = flota.stream()
                .filter(drone -> drone.bateria() < 20)
                .count();

        System.out.println("Consulta 3: " + dronesBateriaCritica);

        // CONSULTA 4: Transformar cada dron en un texto
        List<String> informacionDrones = flota.stream()
                .map(drone ->
                        drone.id() + ": " + drone.bateria() + "%")
                .toList();

        System.out.println("Consulta 4: " + informacionDrones);
    }
}