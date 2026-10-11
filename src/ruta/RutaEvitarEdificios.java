package ruta;

public class RutaEvitarEdificios implements EstrategiaRuta {

    @Override
    public String calcularRuta(String origen, String destino) {
        return "Ruta evitando edificios altos desde "
                + origen + " hasta " + destino;
    }
}
