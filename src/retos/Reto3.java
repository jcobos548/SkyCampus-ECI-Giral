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

public class Reto3 {

    public static void main(String[] args) {

        // Crear un drone de ejemplo
        Drone drone = new Drone(
                "D-03",
                85,
                true,
                "Bloque C",
                3.0
        );

        // Construir una mision con Builder
        Mision mision = new MisionBuilder()
                .drone(drone)
                .origen("Bloque C")
                .destino("Biblioteca")
                .tipoCarga(TipoCarga.PAQUETE_PEQUENO)
                .notas("Entrega de prueba")
                .build();

        // Definir destinos validos
        List<String> destinosValidos = List.of(
                "Biblioteca",
                "Bloque A",
                "Bloque B",
                "Bloque C",
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
        System.out.println("Validando mision...");

        boolean aceptada = cadena.validar(mision);

        System.out.println(
                aceptada
                        ? "Mision aceptada."
                        : "Mision rechazada."
        );
    }
}