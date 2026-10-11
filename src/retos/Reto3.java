package retos;

import builder.MisionBuilder;
import chain.Validador;
import chain.ValidadorBateria;
import chain.ValidadorDestino;
import chain.ValidadorCarga;
import model.Drone;
import model.Mision;
import model.TipoCarga;

import java.util.List;
import java.util.logging.Logger;

public class Reto3 {

    private static final Logger LOGGER =
            Logger.getLogger(Reto3.class.getName());

    private static final String BLOQUE_C = "Bloque C";

    public static void main(String[] args) {

        // Crear un drone de ejemplo
        Drone drone = new Drone(
                "D-03",
                85,
                true,
                BLOQUE_C,
                3.0
        );

        // Construir una misión con Builder
        Mision mision = new MisionBuilder()
                .drone(drone)
                .origen(BLOQUE_C)
                .destino("Biblioteca")
                .tipoCarga(TipoCarga.PAQUETE_PEQUENO)
                .notas("Entrega de prueba")
                .build();

        // Definir destinos válidos
        List<String> destinosValidos = List.of(
                "Biblioteca",
                "Bloque A",
                "Bloque B",
                BLOQUE_C,
                "Bloque D"
        );

        // Construir la cadena en el orden solicitado
        Validador cadena = new ValidadorBateria();

        cadena.conectarCon(
                new ValidadorDestino(destinosValidos)
        ).conectarCon(
                new ValidadorCarga()
        );

        // Ejecutar las validaciones
        LOGGER.info("Validando misión...");

        boolean aceptada = cadena.validar(mision);

        LOGGER.info(
                aceptada
                        ? "Misión aceptada."
                        : "Misión rechazada."
        );
    }
}
