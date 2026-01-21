package org.example;

/**
 * Gilberto
 * Acceso Datos
 * Tarea 1 UT6
 */
import org.xmldb.api.DatabaseManager; // Gestor central de conexiones
import org.xmldb.api.base.Collection; // Representa una colección (carpeta)
import org.xmldb.api.base.Database; // Interfaz del driver
import org.xmldb.api.base.XMLDBException; // Excepción principal de XML:DB
import org.xmldb.api.modules.XMLResource;

import java.lang.reflect.InvocationTargetException; // Para reflexión

public class TestExistConnection {

    //Establecemos los datos de conexion para la base de datos
    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db";
    private static final String User = "admin";
    private static final String Password = "";

    public static void main( String[] args ){

        //Definimos las variables fuera del try-catch porque si no, no
        //las detecta el finally para cerrar la conexion
        Collection col = null;
       // XMLResource res = null; aqui no lo usamos esta variable

        try {
            //CARGAMOS Y REGISTRAMOS EL DRIVER
            final String driver = "org.exist.xmldb.DatabaseImpl";

            // initialize database driver
            Class cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            //database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);



            //Obtenemos la coleccion raiz (/db)
            col = DatabaseManager.getCollection(URI, User, Password);

            if(col == null){
                System.out.println("No se pudo acceder a la coleccion db");
                return;
            }

            System.out.println("Se conecto a correctamente a existDB by Gilber");


            //Listamos las subcolecciones
            String[] colecciones = col.listChildCollections();
            for(String nombre : colecciones){
                System.out.println(nombre);
            }


        }catch (ClassNotFoundException e) {
            System.err.println("No se puedo encontrar la clase del driver");
        }catch (InstantiationException e) {
            System.err.println("Error al crear la instancia del driver");
        }catch (IllegalAccessException e) {
            System.err.println("Error: No hay acceso al constructor");
        }catch (XMLDBException e) {
            System.err.println("Error de conexion, credenciales o permisos ");

        }finally {

            //Cerramos la conexion o recursos
            if(col != null){
                try {
                    col.close();
                    System.out.println("Conexion cerrada correctamente");
                } catch(XMLDBException e) {
                    System.err.println("Error al cerrar la conexion");
                }
            }
        }
    }
}
