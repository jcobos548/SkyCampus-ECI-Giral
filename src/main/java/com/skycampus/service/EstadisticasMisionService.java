package com.skycampus.service;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoMision;
import com.skycampus.model.Mision;
import com.skycampus.model.Prioridad;
import com.skycampus.model.TipoDrone;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EstadisticasMisionService {

    // Punto 1: misiones completadas por tipo de dron
    public Map<TipoDrone, Long> contarCompletadasPorTipo(
            List<Mision> misiones) {

        return misiones.stream()
                .filter(m -> m.estado() == EstadoMision.COMPLETADA)
                .collect(Collectors.groupingBy(
                        m -> m.drone().tipo(),
                        Collectors.counting()
                ));
    }

    // Punto 2: dron con más misiones completadas
    public Optional<Drone> encontrarDroneConMasMisionesCompletadas(
            List<Mision> misiones) {

        Map<String, Long> conteoPorDrone = misiones.stream()
                .filter(m -> m.estado() == EstadoMision.COMPLETADA)
                .collect(Collectors.groupingBy(
                        m -> m.drone().id(),
                        Collectors.counting()
                ));

        return conteoPorDrone.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .flatMap(maximo ->
                        misiones.stream()
                                .filter(m -> m.drone().id()
                                        .equals(maximo.getKey()))
                                .map(Mision::drone)
                                .findFirst()
                );
    }

    // Punto 3: porcentaje de misiones fallidas sobre el total
    public double calcularPorcentajeFallidas(List<Mision> misiones) {

        if (misiones.isEmpty()) {
            return 0.0;
        }

        long fallidas = misiones.stream()
                .filter(m -> m.estado() == EstadoMision.FALLIDA)
                .count();

        return fallidas * 100.0 / misiones.size();
    }

    // Punto 4: misión urgente pendiente hace más de 10 minutos
    public boolean existeUrgentePendienteVencida(
            List<Mision> misiones,
            LocalDateTime ahora) {

        LocalDateTime limite = ahora.minusMinutes(10);

        return misiones.stream()
                .anyMatch(m ->
                        m.prioridad() == Prioridad.URGENTE
                                && m.estado() == EstadoMision.PENDIENTE
                                && m.fechaCreacion().isBefore(limite)
                );
    }
}