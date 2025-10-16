import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class LogActividad3 {
    public static void main(String[] args) {

        //*****Gilberto Gil Gandia*****// Tarea 3 Leer y escribir fichero
        //Gestion Basica de flujos de datos


        //1ºNombre del fichero de log que voy a crear
        //final xq no quiero q se modifique
        final String Nombre_Fichero = "seguridad_actividad3.log";

        //2ºEscribir en el fichero dentro de un bloque try/catch

        try (FileWriter writer = new FileWriter("seguridad_actividad3.log")) {
        //Escribimos: Intento de acceso fallido
        //            Uusario autenticado correctamente
           writer.write("Intento de acceso fallido");
           writer.write("\n");
           writer.write("Usuario autenticado correctamente");

            System.out.println("Escritura completa en" + "seguridad_actividad3.log");

        }catch(Exception e){
            System.out.println("Error: No se pudo escribir en el fichero: " + e.getMessage());

        }

        //3ºLeemos fichero
        //Abrimos el mismo fichero para la lectura y mostramos su contenido

        try (FileReader reader = new FileReader("seguridad_actividad3.log")) {
            BufferedReader bR = new BufferedReader(reader);

            System.out.println("Leyendo contenido de: " +"seguridad_actividad3.log" + ".");
            String linea;
            while ((linea = bR.readLine()) != null) {
                System.out.println(linea);
            }

        }catch(Exception e){
            //Las excepciones mejor manejarlas con err y no con out
            System.err.println("Error: No se pudo leer el fichero: " + e.getMessage());
        }
    }
}