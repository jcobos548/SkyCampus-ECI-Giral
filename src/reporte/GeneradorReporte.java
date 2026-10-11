
package reporte;

import model.Mision;

import java.util.List;
import java.util.logging.Logger;

public class GeneradorReporte {

    private static final Logger LOGGER =
            Logger.getLogger(GeneradorReporte.class.getName());

    public void generarReportePDF(List<Mision> misiones) {
        // Aquí va la lógica para generar el PDF con iText.
        LOGGER.log(
                java.util.logging.Level.INFO,
                "Generando reporte de {0} misiones.",
                misiones.size()
        );
    }
}
