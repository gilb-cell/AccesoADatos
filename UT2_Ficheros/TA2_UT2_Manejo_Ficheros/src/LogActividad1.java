import java.io.File;

public class LogActividad1 {

    public static void main(String[] args) {
        //Creamos una instancia de la clase file con la ruta de nuestro fichero
        File miFichero = new File ("./seguridad_actividad1.log");

        //Comprobamos si existe ese fichero
        if(miFichero.exists()) {
            System.out.println("El archivo esta encontrado.");
        }else{
            System.out.println("El archivo no existe");
        }
    }
}


