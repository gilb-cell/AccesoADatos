import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestionFichero {
    public static void main(String[] args) {

        //Variable
        final String archivoRenombrado = "accesos_G_I_L.txt";

        //Creamos el directorio
        File direc = new File("accesos/seguridad");
        if (!direc.exists()) {
            if (direc.mkdirs()) {
                System.out.println("Directorio creado: " + direc.getAbsolutePath());
            } else {
                System.out.println("No se pudo crear el directorio.");
                return;
            }
        } else {
            System.out.println("El directorio ya existe: " + direc.getAbsolutePath());
        }

        // Creamos el fichero
        File fichero = new File(direc, "accesos.txt");
        try {
            if (fichero.createNewFile()) {
                System.out.println("Fichero creado: " + fichero.getAbsolutePath());
            } else {
                System.out.println("El fichero ya existe: " + fichero.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error al crear el fichero: " + e.getMessage());
            return;
        }

        //Renombrado del fichero
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        File rotate = new File(direc, "accesos_G_I_L " + timestamp + ".txt");

        if (fichero.renameTo(rotate)) {
            System.out.println("Fichero renombrado a: " + rotate.getName());
        } else {
            System.out.println("No se pudo renombrar el fichero.");
            return;
        }


        //Escritura en el fichero renombrado , abrirlo y escribir
        try (FileReader leemos = new FileReader("accesos_G_I_L.txt")){
            BufferedReader br = new BufferedReader(leemos);

            System.out.println("Leyendo contenido de: " + "accesos_G_I_L.txt");

           String linea;
            while ((linea = br.readLine()) != null){
                System.out.println(linea);
            }
        }catch(Exception e){
            System.err.println("Error: No se pudo leer el contenido del fichero:" + "\n"+ e.getMessage());

        }

        try (FileWriter fw = new FileWriter(archivoRenombrado)) {
            fw.write("\n[INFO] Inicio de registro de actividad.");
            fw.write("\n[INFO] Registro generado correctamente.");

            System.out.println("\n La escritura ha sido exitosa " + archivoRenombrado);

        } catch (IOException e) {
            System.err.println("Error:No se puede escribir en el archivo: " + e.getMessage());
        }

        //  Eliminacion
        if (rotate.delete()) {
            System.out.println("Fichero eliminado correctamente: " + rotate.getName());
        } else {
            System.out.println("No se pudo eliminar el fichero.");
        }

    }
}
