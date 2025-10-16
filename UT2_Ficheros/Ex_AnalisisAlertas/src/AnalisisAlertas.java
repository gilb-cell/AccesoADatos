import java.io.*;

public class AnalisisAlertas {
    public static void main(String[] args) {

        final String archivoOriginal = "alertas.txt";
        final String archivoCopia = "alertas_limpio.txt";

        //Abrimos el mismo fichero para la lectura y mostramos su contenido

        try (FileReader reader = new FileReader("alertas.txt")) {
            BufferedReader bR = new BufferedReader(reader);

            System.out.println("Leyendo contenido de: " +"alertas.txt" + ".");
            String linea;
            while ((linea = bR.readLine()) != null) {
                System.out.println(linea);
            }

        }catch(Exception e){
            System.err.println("Error: No se pudo leer el fichero: " + e.getMessage());
        }

        //Contar caracteres
        int contadorCaracteres = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contadorCaracteres += linea.length();
               // String[] palabras = linea.split("\\s+");

            }

            System.out.println("\n ***Conteo***");
            System.out.println("Numero de caracteres: " + contadorCaracteres);

        } catch (IOException e) {
            System.out.println("Error: No se puede contar el contenido: " + e.getMessage());
        }

        //Limpieza de contenido
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             PrintWriter pw = new PrintWriter(new FileWriter(archivoCopia))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                pw.println(linea);
            }
            System.out.println("\n Se ha copiado el contenido a : " + archivoCopia);

        } catch (IOException e) {
            System.err.println("Error:No se puede copiar el archivo: " + e.getMessage());
        }
    //Eliminador la palabra virus
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCopia))) {
            StringBuilder contenidoLimpio = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.replaceAll("virus", "");
                contenidoLimpio.append(linea).append("\n");
            }

            try (FileWriter fw = new FileWriter(archivoCopia)) {
                fw.write(contenidoLimpio.toString());
            }

            System.out.println("\n Se han eliminado las apariciones de la palabra \"virus\" en: " + archivoCopia);

        } catch (IOException e) {
            System.err.println("Error al limpiar el archivo copia: " + e.getMessage());
        }

       // Mostrar el contenido final de alertas_limpio.txt
        System.out.println("\n Contenido final:  " + archivoCopia );
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCopia))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error:No se puede mostrar el archivo copia: " + e.getMessage());
        }
    }
}