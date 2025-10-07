package org.example;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Clase DeserializarUsuario encargada de leer un archivo XML
 * y convertirlo en un objeto  Usuario mediante la API JAXB.
 * El flujo de trabajo de la clase es:
 *  Comprobar si existe el archivo XML especificado.
 *  Configurar el contexto JAXB para la clase Usuario.
 *  Crear un Unmarshaller para convertir XML a objeto.
 *  Deserializar el archivo y mostrar los datos en consola.
 *
 * @author Gilberto
 * @since 07/10/2025
 * @version 1.0
 */
public class DeserializarUsario {

    /**
     * Metodo principal de la aplicación.
     * Verifica si el archivo  usuario.xml existe en el directorio actual,
     * lo deserializa utilizando JAXB y muestra los datos del usuario.
     *
     */
    public static void main(String[] args) {

        // 1. Comprobamos si existe el fichero usuario.xml
        File xmlFile = new File("usuario.xml");
        if (!xmlFile.exists()) {
            System.out.println(" No se encontro el archivo usuario.xml");
            return;
        }

        try {
            // 2. Creamos el contexto JAXB para la clase Usuario
            JAXBContext context = JAXBContext.newInstance(Usuario.class);

            // 3. Creamos el Unmarshaller que convierte XML a objeto Java
            Unmarshaller um = context.createUnmarshaller();

            // 4. Deserializamos el archivo XML: obtenemos el objeto Usuario
            Usuario usuario = (Usuario) um.unmarshal(xmlFile);

            // 5. Mostramos los datos obtenidos del usuario en consola
            System.out.println("Datos del usuario deserializado:");
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Rol: " + usuario.getRol());

        } catch (Exception e) {
            // Manejamos cualquier excepción lanzada durante el proceso
            System.err.println(" Error durante la deserialización:");
            e.printStackTrace(System.err);
        }
    }
}

//GILBERTO//
