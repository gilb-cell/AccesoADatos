import java.io.*;

public class AnalisisFich {
    public static void main(String[] args) {

        //Gilberto Gil Gandia//

        //Creamos dos variables estaticas que iremos usando a lo largo del codigo
        final String archivoOriginal = "seguridad.txt";
        final String archivoCopia = "seguridadCopia.txt";
    //Abrimos fichero seguridad.txt y mostramos el contenido

       try (FileReader leemos = new FileReader("seguridad.txt")){
           BufferedReader br = new BufferedReader(leemos);

           System.out.println("Leyendo contenido de: " + "seguridad.txt");

           String linea;
           while ((linea = br.readLine()) != null){
               System.out.println(linea);
           }
       }catch(Exception e){
           System.out.println("Error: No se pudo leer el contenido del fichero:" + "\n"+ e.getMessage());

       }

        // 2. Añadir nuevas líneas al archivo original
        try (FileWriter fw = new FileWriter(archivoOriginal, true)) { // true = añadir sin borrar
            fw.write("\n[INFO] Nuevo intento de inicio de sesión.");
            fw.write("\n[WARNING] Contraseña incorrecta introducida tres veces.");
            System.out.println("\n Se han añadido nuevas líneas al archivo " + archivoOriginal);
        } catch (IOException e) {
            System.out.println("Error:No se puede escribir en el archivo: " + e.getMessage());
        }

        // 3. Mostrar el contenido después de añadir las líneas
        System.out.println("\n Contenido actualizado de : " + archivoOriginal );
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error: No se puede leer el archivo: " + e.getMessage());
        }

        // 4. Contar líneas, palabras y caracteres
        //Creamos las variables y las iniciamos en 0
        int contadorLineas = 0;
        int contadorPalabras = 0;
        int contadorCaracteres = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contadorLineas++;
                contadorCaracteres += linea.length();
                String[] palabras = linea.split("\\s+");
                contadorPalabras += palabras.length;
            }

            System.out.println("\n ***Conteo***");
            System.out.println("Numero de lineas: " + contadorLineas);
            System.out.println("Numero de palabras: " + contadorPalabras);
            System.out.println("Numero de caracteres: " + contadorCaracteres);

        } catch (IOException e) {
            System.out.println("Error: No se puede contar el contenido: " + e.getMessage());
        }

        // 5. Copiar el contenido a seguridad_copia.txt
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             PrintWriter pw = new PrintWriter(new FileWriter(archivoCopia))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                pw.println(linea);
            }
            System.out.println("\n Se ha copiado el contenido a : " + archivoCopia);

        } catch (IOException e) {
            System.out.println("Error:No se puede copiar el archivo: " + e.getMessage());
        }

        // 6. Eliminar todas las apariciones de "contraseña" en el archivo copia
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCopia))) {
            StringBuilder contenidoLimpio = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                // reemplaza "contraseña" (sin distinción de mayúsculas/minúsculas)
                linea = linea.replaceAll("contraseña", "");
                contenidoLimpio.append(linea).append("\n");
            }

            try (FileWriter fw = new FileWriter(archivoCopia)) {
                fw.write(contenidoLimpio.toString());
            }

            System.out.println("\n Se han eliminado las apariciones de la palabra \"contraseña\" en: " + archivoCopia);

        } catch (IOException e) {
            System.out.println("Error al limpiar el archivo copia: " + e.getMessage());
        }

        // 7. Mostrar el contenido final de seguridad_copia.txt
        System.out.println("\n Contenido final:  " + archivoCopia );
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCopia))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error:No se puede mostrar el archivo copia: " + e.getMessage());
        }

        System.out.println("\n ***Proceso completado y finalizado***");
    }
}


