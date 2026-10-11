package reporte;

import model.Mision;
import java.util.List;

public class GeneradorReporte {

    public void generarReportePDF(List<Mision> misiones) {
        // Aquí va la lógica para generar el PDF con iText.
        System.out.println(
                "Generando reporte de " + misiones.size() + " misiones."
        );
    }
}