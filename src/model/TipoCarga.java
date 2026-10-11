package model;

public enum TipoCarga {
    DOCUMENTOS(0.5),
    PAQUETE_PEQUENO(2.0),
    PAQUETE_GRANDE(5.0);

    private final double pesoKg;

    TipoCarga(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public double pesoKg() {
        return pesoKg;
    }
}