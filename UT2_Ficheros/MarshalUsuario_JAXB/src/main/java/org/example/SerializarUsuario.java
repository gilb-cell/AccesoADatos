package org.example;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;


import java.io.File;

/**
 * @author Gilberto
 * @version 1.0
 * @since 09/10/2025
 */

public class SerializarUsuario {
    public static void main( String[] args ) {
        //1. Creamos el objeto a serializar
        Usuario u = new Usuario("1", "Gilberto", "gilb@example.com", "Administrador");


        try {
            //2 Preparar JAXB para la clase Usuario
            JAXBContext context = JAXBContext.newInstance(Usuario.class);


            //El Marshaller es el que "convierte" el objeto a XML
            Marshaller marshaller = context.createMarshaller();


            //Formatear la salida del XML con saltos y sangrías para que sea legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


            //3 Serializar a archivo: se creará (o sobreescribirá) "usuario.xml" en el directorio
            File salida = new File("usuario.xml");
            marshaller.marshal(u, salida);


            System.out.println("n\n[OK] Archivo generado: " + salida.getAbsolutePath());


        } catch (JAXBException e) {
            System.err.println("Error al serializar el usuario: " + e.getMessage());
        }
    }
}
