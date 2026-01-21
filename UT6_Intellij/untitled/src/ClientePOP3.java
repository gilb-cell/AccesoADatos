
import org.apache.commons.net.pop3.POP3Client;
import org.apache.commons.net.pop3.POP3MessageInfo;
import java.io.BufferedReader;

/**
 * ClientePOP3
 * Gilberto
 * PSP Tarea opcional
 */

public class ClientePOP3 {

    public static void main(String[] args) {

        // Dirección del servidor POP3
        String servidor = "pop.gmail.com";

        // Puerto POP3 con SSL
        int puerto = 995;

        // Credenciales de acceso a una cuenta
        String usuario = "correo@gmail.com";
        String password = "password";

        // Creamos el cliente POP3
        POP3Client cliente = new POP3Client();

        // Establecemos un tiempo máximo de espera para evitar bloqueos
        cliente.setDefaultTimeout(10000);

        try {

            //CONEXIÓN AL SERVIDOR
            cliente.connect(servidor, puerto);

            // Iniciamos sesión con usuario y contraseña
            boolean loginCorrecto = cliente.login(usuario, password);

            // Comprobamos si el login ha sido correcto
            if (!loginCorrecto) {
                System.out.println("Error al iniciar sesion en el servidor POP3.");
                return;
            }

            System.out.println("Conectado correctamente al servidor POP3.");


            // INFORMACIÓN DEL BUZÓN

            // Obtenemos la lista de mensajes del buzón
            POP3MessageInfo[] mensajes = cliente.listMessages();

            // Si no hay mensajes, informamos al usuario
            if (mensajes == null || mensajes.length == 0) {
                System.out.println("No hay mensajes en el buzón.");
                return;
            }

            System.out.println("Numero de mensajes disponibles: " + mensajes.length);


            //RECUPERAR UN MENSAJE
            System.out.println(" Leyendo el primer mensaje ");

            // Recuperamos el mensaje número 1
            BufferedReader lector = new BufferedReader(cliente.retrieveMessage(1));

            String linea;
            int contadorLineas = 0;

            // Leemos solo las primeras líneas para no saturar la consola
            while ((linea = lector.readLine()) != null && contadorLineas < 20) {
                System.out.println(linea);
                contadorLineas++;
            }

            // Cerramos el lector
            lector.close();

        } catch (Exception e) {
            // Captura de cualquier error de conexión o lectura
            System.out.println("Error durante la conexion o lectura del correo.");
            e.printStackTrace();

        } finally { //Bloque finally para cerrar recursos

            // CIERRE DE LA CONEXION

            try {
                // Cerramos sesion y desconectamos correctamente
                if (cliente.isConnected()) {
                    cliente.logout();      // Enviamos QUIT al servidor
                    cliente.disconnect(); // Cerramos el socket
                    System.out.println("Conexion cerrada correctamente.");
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion POP3.");
            }
        }
    }
}


