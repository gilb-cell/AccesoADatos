
import java.io.RandomAccessFile;
 */


import java.io.IOException;

public class AccesoUsuarios {
    public static void main(String[] args) {

        //****  Gilberto ****///

        try (RandomAccessFile archivo = new RandomAccessFile("usuarios.dat", "rw")) {

            // Tamaño de cada registro
            int sizeRegistro = 10; // ID + espacios + nombre + salto de línea

            // 1º Mover el puntero al inicio del registro 003
            long posicion = (3 - 1) * sizeRegistro;  // 3º usuario empieza después de 2 registros
            archivo.seek(posicion);

            // Leer la línea
            String usuario = archivo.readLine();
            System.out.println("El usuario 003 es: " + usuario);

            // Cierre después de la primera operación
            try {
                archivo.close();
                System.out.println("Archivo cerrado tras leer el usuario 003.\n");
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }

            // 2º Reabrir archivo para modificar usuario 002 a Pedro
            RandomAccessFile archivo2 = new RandomAccessFile("usuarios.dat", "rw");
            long posicion2 = (2 - 1) * sizeRegistro;
            archivo2.seek(posicion2);
            archivo2.writeBytes("002 Pedro\n");
            System.out.println("Usuario 002 modificado a Pedro.");

            //Cierre después de la segunda operación
            try {
                archivo2.close();
                System.out.println("Archivo cerrado tras modificar el usuario 002.\n");
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }

            // 3º Reabrir archivo para añadir usuario 006
            RandomAccessFile archivo3 = new RandomAccessFile("usuarios.dat", "rw");
            archivo3.seek(archivo3.length());
            archivo3.writeBytes("006 Ana \n");
            System.out.println("Usuario 006 añadido.");

            // Cierre después de la tercera operación
            try {
                archivo3.close();
                System.out.println("Archivo cerrado tras añadir el usuario 006.\n");
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }

            // 4º Reabrir archivo para eliminar usuario 004
            RandomAccessFile archivo4 = new RandomAccessFile("usuarios.dat", "rw");
            long posicionEliminar = (4 - 1) * sizeRegistro;
            archivo4.seek(posicionEliminar);
            archivo4.writeBytes("004    \n");
            System.out.println("Usuario 004 eliminado.");

            // Cierre después de la cuarta operación
            try {
                archivo4.close();
                System.out.println("Archivo cerrado tras eliminar el usuario 004.\n");
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }

            // 5º Reabrir archivo para mostrar contenido final
            RandomAccessFile archivo5 = new RandomAccessFile("usuarios.dat", "r");
            archivo5.seek(0);
            System.out.println("\nContenido final del archivo:");
            String linea;
            while ((linea = archivo5.readLine()) != null) {
                System.out.println(linea);
            }

            // Cierre después de mostrar contenido
            try {
                archivo5.close();
                System.out.println("\nArchivo cerrado tras mostrar el contenido final.");
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error el registro no es correcto: " + e.getMessage());
        }
    }



//Solucion Lorem Tarea5

import java.io.*;


public class SolucionByLoren {
public static void main(String[] args) {
   //Creo y escribo en el archivo inicial los datos que nos da el
   //enunciado dentro de un bloque try catch
   try {
       // Crear objeto File que apunta al archivo en el directorio del proyecto
       File archivo = new File("usuarios.dat");


       // FileWriter para escribir texto (true = añadir si ya existe)
       FileWriter fw = new FileWriter(archivo);
       BufferedWriter bw = new BufferedWriter(fw);


       // Escribimos los registros
       //Mejor sin tildes para evitar problemas
       bw.write("001 Juan");
       bw.newLine();
       bw.write("002 María");
       bw.newLine();
       bw.write("003 Mila");
       bw.newLine();
       bw.write("004 Abraham");
       bw.newLine();
       bw.write("005 Carlos");
       bw.newLine();


       // Cerrar recursos
       bw.close();


       System.out.println("Archivo creado correctamente.");


   } catch (IOException e) {
       System.out.println("Error al crear o escribir el archivo: " + e.getMessage());
   }


   //Otro bloque try/catch trabajando con RandomAccessFile
   try (RandomAccessFile archivo = new RandomAccessFile("usuarios.dat", "rw")){
       //Lectura aleatoria: acceder al empleado 003
       archivo.seek(19); // Posición donde comienza "003 Mila"
       String empleado003 = archivo.readLine(); //leer linea correspondiente
       System.out.println("Empleado 003: " + empleado003); //mostrar resultado


       //Modificación de datos: Cambiar nombre del empleado 002 a pedro
       //Al sobreescribir es importante que el nuevo registro tenga los mismos caracteres q el anterior!!
       archivo.seek(9);
       archivo.writeBytes("002 Pedro\n");


       //Mostrar contenido del archivo después de la modificación
       archivo.seek(0); //Volver al inicio del archivo
       System.out.println("\nContenido después de la modificación:");
       String line;
       while ((line = archivo.readLine()) != null) { //leer cada línea del archivo
           System.out.println(line); //mostrar cada linea


       }


       //Añadir un nuevo registro: Empleado 006: Ana
       archivo.seek(archivo.length()); //posición al final del archivo
       archivo.writeBytes("006 Ana\n"); //escribir el nuevo registro
   } catch (IOException e) {
       System.err.println(e.getMessage());
   }

}

}



//Tarea 6  Parser DOM// El perse es para analizar y leer el XML Sax(secuencial) y Dom (jerarquico)
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
    /**
     * La clase LeerLogs permite leer y procesar un archivo XML llamado logs.xml.
     * Utiliza el API DOM (Document Object Model) para cargar el archivo XML,
     * recorrer los nodos y mostrar el contenido de cada log en la consola.
     * El XML debe tener una estructura donde cada elemento <log> contenga
     * los subelementos <nivel>, <mensaje> y <usuario>, además de un atributo id.
     *
     * @author  Gilberto
     * @version 1.0
    Módulo: ACCESO A DATOS
     */
    public class LeerLogs {
        /**
         * Metodo principal para ejecutar el programa.
         * Este metodo:
         * Crea un objeto Document a partir del archivo logs.xml.
         * Recorre todos los elementos <log> y extrae sus datos.
         * Muestra la información de cada log en la consola.
         *
         */
        public static void main(String[] args) {
            // Cargamos el archivo XML que contiene los logs
            File file = new File("logs.xml");
            try {
                /**
                 * Fabrica de constructores de documentos DOM que nos permite crear
                 * objetos para analizar archivos XML.
                 */
                DocumentBuilderFactory dbFactory =
                        DocumentBuilderFactory.newInstance();
                /**
                 * Constructor de documentos para convertir el archivo XML
                 * en un arbol DOM manipulable.
                 */
                DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
                /**
                 * Se analiza el archivo XML y se almacena en un objeto  Document.
                 */
                Document doc = dbBuilder.parse(file);
                doc.getDocumentElement();
                /**
                 * Obtenemos una lista de todos los elementos <log> del archivo XML
                 * usando doc.getElementsByTagName(String).
                 */
                NodeList logs = doc.getElementsByTagName("log");
                // Recorremos la lista de logs y mostramos sus datos
                for (int i = 0; i < logs.getLength(); i++) {
                    Element logElement = (Element) logs.item(i);
                    // Obtenemos el atributo "id" del log
                    String id = logElement.getAttribute("id");
                    // Obtenemos el contenido de los subelementos
                    String nivel =
                            logElement.getElementsByTagName("nivel").item(0).getTextContent();
                    String mensaje =
                            logElement.getElementsByTagName("mensaje").item(0).getTextContent();
                    String usuario =
                            logElement.getElementsByTagName("usuario").item(0).getTextContent();
                    Módulo: ACCESO A
                    DATOS
                    // Mostramos los datos por consola
                    System.out.println("ID: " + id);
                    System.out.println("Nivel: " + nivel);
                    System.out.println("Mensaje: " + mensaje);
                    System.out.println("Usuario: " + usuario);
                }
            } catch (Exception e) {
                // Si ocurre algún error al procesar el XML, mostramos error
                e.printStackTrace();
            }
        }
    }



