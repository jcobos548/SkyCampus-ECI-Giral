package notificacion;

public class AlertaOperadorEmail
        implements AlertaOperador {

    @Override
    public void enviar(String operador, String mensaje) {
        // Aquí va la lógica SMTP.
        System.out.println(
                "Enviando correo a " + operador + ": " + mensaje
        );
    }
}
