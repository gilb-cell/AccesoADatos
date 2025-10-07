import java.io.RandomAccessFile;


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
}

