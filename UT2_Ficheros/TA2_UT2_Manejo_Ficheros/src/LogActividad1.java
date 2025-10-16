import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LogActividad1 {

    //GILBERTO// Tarea 2
//Creacion, renombrar y eliminar ficheros

    public static void main(String[] args) {

    /*    //Creamos una instancia de la clase file con la ruta de nuestro fichero
        File miFichero = new File ("./logs_seguridad");

        //Comprobamos si existe ese fichero
        if(miFichero.exists()) {
            System.out.println("El archivo esta encontrado.");
        }else{
            System.out.println("El archivo no existe");
        }*/

        // 1. Crear directorio logs/seguridad
                File dir = new File("logs/seguridad");
                if (!dir.exists()) {
                    if (dir.mkdirs()) {  //mkdir crea un directorio
                        System.out.println("Directorio creado: " + dir.getAbsolutePath());
                    } else {
                        System.out.println("No se pudo crear el directorio.");
                        return;
                    }
                } else {
                    System.out.println("El directorio ya existía: " + dir.getAbsolutePath());
                }

                // 2. Crear fichero seguridad_actividad1.log
                File logFile = new File(dir, "seguridad_actividad1.log");
                try {
                    if (logFile.createNewFile()) {
                        System.out.println("Fichero creado: " + logFile.getAbsolutePath());
                    } else {
                        System.out.println("El fichero ya existía: " + logFile.getAbsolutePath());
                    }
                } catch (IOException e) {
                    System.out.println("Error al crear el fichero: " + e.getMessage());
                    return;
                }

               // 3. Rotación por renombrado con fecha y hora
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
                File rotatedFile = new File(dir, "seguridad_actividad1_" + timestamp + ".log");

                if (logFile.renameTo(rotatedFile)) {
                    System.out.println("Fichero renombrado a: " + rotatedFile.getName());
                } else {
                    System.out.println("No se pudo renombrar el fichero.");
                    return;
                }

                // 4. Eliminación (simulación de retención)
                if (rotatedFile.delete()) {
                    System.out.println("Fichero eliminado correctamente: " + rotatedFile.getName());
                } else {
                    System.out.println("No se pudo eliminar el fichero.");
                }
            }
        }





