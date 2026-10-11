package model;

public record Mision(
        Drone drone,
        String origen,
        String destino,
        String notas,
        int prioridad,
        TipoCarga tipoCarga
) {
}
