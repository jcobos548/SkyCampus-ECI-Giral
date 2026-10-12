package com.skycampus.retos;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;
import com.skycampus.model.EstadoMision;
import com.skycampus.model.Mision;
import com.skycampus.model.Prioridad;
import com.skycampus.model.TipoDrone;
import com.skycampus.service.EstadisticasMisionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Reto1 {

    public static void main(String[] args) {

        LocalDateTime ahora = LocalDateTime.now();

        Drone drone1 = new Drone(
                "D001", TipoDrone.MINI, 90, true, EstadoDrone.DISPONIBLE
        );

        Drone drone2 = new Drone(
                "D002", TipoDrone.CARGO, 75, false, EstadoDrone.EN_VUELO
        );

        Drone drone3 = new Drone(
                "D003", TipoDrone.EXPRESS, 60, false, EstadoDrone.EN_VUELO
        );

        List<Mision> misiones = List.of(
                new Mision(
                        "M001", drone1, "Bogotá", 300,
                        Prioridad.NORMAL, EstadoMision.COMPLETADA,
                        ahora.minusMinutes(40)
                ),
                new Mision(
                        "M002", drone2, "Chía", 1500,
                        Prioridad.URGENTE, EstadoMision.COMPLETADA,
                        ahora.minusMinutes(30)
                ),
                new Mision(
                        "M003", drone2, "Cajicá", 1200,
                        Prioridad.NORMAL, EstadoMision.COMPLETADA,
                        ahora.minusMinutes(20)
                ),
                new Mision(
                        "M004", drone3, "Zipaquirá", 500,
                        Prioridad.URGENTE, EstadoMision.PENDIENTE,
                        ahora.minusMinutes(15)
                ),
                new Mision(
                        "M005", drone1, "Bogotá", 200,
                        Prioridad.BAJO, EstadoMision.FALLIDA,
                        ahora.minusMinutes(10)
                )
        );

        EstadisticasMisionService servicio =
                new EstadisticasMisionService();

        // Punto 1: misiones completadas por tipo de dron
        Map<TipoDrone, Long> completadas =
                servicio.contarCompletadasPorTipo(misiones);

        System.out.println("1. Misiones completadas por tipo:");
        completadas.forEach((tipo, cantidad) ->
                System.out.println(tipo + ": " + cantidad)
        );

        // Punto 2: dron con más misiones completadas
        Optional<Drone> droneMayor =
                servicio.encontrarDroneConMasMisionesCompletadas(misiones);

        System.out.println("\n2. Dron con más misiones completadas:");
        droneMayor.ifPresentOrElse(
                drone -> System.out.println(drone.id()),
                () -> System.out.println("No hay misiones completadas")
        );

        // Punto 3: porcentaje de misiones fallidas
        double porcentaje =
                servicio.calcularPorcentajeFallidas(misiones);

        System.out.printf(
                "%n3. Porcentaje de misiones fallidas: %.2f%%%n",
                porcentaje
        );

        // Punto 4: misión urgente pendiente hace más de 10 minutos
        boolean urgenteVencida =
                servicio.existeUrgentePendienteVencida(misiones, ahora);

        System.out.println(
                "\n4. ¿Existe una misión urgente pendiente hace más de 10 minutos? "
                        + urgenteVencida
        );
    }
}