package ruta;

public class RutaDirecta implements EstrategiaRuta {

    @Override
    public String calcularRuta(String origen, String destino) {
        return "Ruta directa desde " + origen + " hasta " + destino;
    }
}
