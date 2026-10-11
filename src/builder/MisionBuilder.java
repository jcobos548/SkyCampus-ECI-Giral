package builder;

import model.Drone;
import model.Mision;
import model.TipoCarga;

public class MisionBuilder {

    private Drone drone;
    private String origen;
    private String destino;
    private String notas = "";
    private int prioridad = 3;
    private TipoCarga tipoCarga;

    public MisionBuilder drone(Drone drone) {
        this.drone = drone;
        return this;
    }

    public MisionBuilder origen(String origen) {
        this.origen = origen;
        return this;
    }

    public MisionBuilder destino(String destino) {
        this.destino = destino;
        return this;
    }

    public MisionBuilder notas(String notas) {
        this.notas = notas;
        return this;
    }

    public MisionBuilder prioridad(int prioridad) {
        this.prioridad = prioridad;
        return this;
    }

    public MisionBuilder tipoCarga(TipoCarga tipoCarga) {
        this.tipoCarga = tipoCarga;
        return this;
    }

    public Mision build() {
        if (drone == null || origen == null || destino == null) {
            throw new IllegalStateException(
                    "drone, origen y destino son obligatorios"
            );
        }

        if (tipoCarga == null) {
            throw new IllegalStateException(
                    "El tipo de carga es obligatorio"
            );
        }

        return new Mision(
                drone,
                origen,
                destino,
                notas,
                prioridad,
                tipoCarga
        );
    }
}