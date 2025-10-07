import java.io.RandomAccessFile;

public class CrearArchivoUsuario {
    public static void main(String[] args) {

        try{
            //Creo el archivo en modo lectura/escritura
            RandomAccessFile archivo=new RandomAccessFile("usuarios.dat","rw");

            // Escribo los registros de los usuarios
            archivo.writeBytes("001 Juan \n");
            archivo.writeBytes("002 Pepe \n");
            archivo.writeBytes("003 Mila \n");
            archivo.writeBytes("004 Reda \n");
            archivo.writeBytes("005 Abel \n");

            archivo.close();
            System.out.println("El archivo se ha creado correctamente.");

        }catch(Exception e){
            System.out.println("Error al crear el archivo: "+e.getMessage());
        }
    }
}