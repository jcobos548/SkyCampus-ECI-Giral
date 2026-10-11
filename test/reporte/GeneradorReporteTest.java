
package reporte;

import model.Drone;
import model.Mision;
import model.TipoCarga;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GeneradorReporteTest {

    @Test
    void debeGenerarReporteConListaVacia() {
        GeneradorReporte generador = new GeneradorReporte();

        assertDoesNotThrow(
                () -> generador.generarReportePDF(List.of())
        );
    }

    @Test
    void debeGenerarReporteConMisiones() {
        Drone drone = new Drone(
                "D-01", 85, true, "Bloque A", 3.0
        );

        Mision mision = new Mision(
                drone,
                "Bloque A",
                "Biblioteca",
                "Entrega de prueba",
                1,
                TipoCarga.PAQUETE_PEQUENO
        );

        GeneradorReporte generador = new GeneradorReporte();

        assertDoesNotThrow(
                () -> generador.generarReportePDF(List.of(mision))
        );
    }
}

