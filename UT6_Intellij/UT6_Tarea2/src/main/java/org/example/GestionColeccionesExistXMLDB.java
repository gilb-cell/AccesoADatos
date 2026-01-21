package org.example;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;


/**
 * Gilberto
 * UT6 - Tarea 2
 * Acceso a Datos
 * Gestión de colecciones en eXistDB usando XML:DB
 */


public class GestionColeccionesExistXMLDB {

    // Datos de conexión
    private static final String DRIVER = "org.exist.xmldb.DatabaseImpl";
    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db";
    private static final String USER = "admin";
    private static final String PASS = "";

    public static void main(String[] args) {

        Collection col = null;
        Collection colFormacion = null;

        try {
            // CARGAR Y REGISTRAR EL DRIVER
            Class cl = Class.forName(DRIVER);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);

            //OBTENER LA COLECCIÓN RAÍZ (/db)
            col = DatabaseManager.getCollection(URI, USER, PASS);

            if (col == null) {
                System.out.println("No se pudo acceder a la colección raíz /db");
                return;
            }

            System.out.println("Conectado correctamente a eXistDB");

            //OBTENER SERVICIO DE GESTIÓN DE COLECCIONES
            CollectionManagementService cms =
                    (CollectionManagementService) col.getService(
                            "CollectionManagementService", "1.0");

            //OBTENER /db/Formacion (si no existe, se crea)
            colFormacion = col.getChildCollection("Formacion");

            if (colFormacion == null) {
                colFormacion = cms.createCollection("Formacion");
                System.out.println("Colección /db/Formacion creada");
            } else {
                System.out.println("La colección /db/Formacion ya existe");
            }

            // Servicio para trabajar dentro de /db/Formacion
            CollectionManagementService cmsFormacion =
                    (CollectionManagementService) colFormacion.getService(
                            "CollectionManagementService", "1.0");

            // CREAR SUBCOLECCIÓN Alumnos
            Collection colAlumnos = cmsFormacion.createCollection("Alumnos");
            if (colAlumnos != null) {
                System.out.println("Colección /db/Formacion/Alumnos creada correctamente");
                colAlumnos.close();
            }

            // CREAR SUBCOLECCIÓN
            Collection colError = cmsFormacion.createCollection("Error");
            if (colError != null) {
                System.out.println("Colección /db/Formacion/Error creada correctamente");
                colError.close();
            }

            //ELIMINAR LA COLECCIÓN
          /*  cmsFormacion.removeCollection("Error");
            System.out.println("Colección /db/Formacion/Error eliminada correctamente");
*/
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el driver de eXistDB");

        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("Error al crear la instancia del driver");

        } catch (XMLDBException e) {
            System.err.println("Error de eXistDB: " + e.getMessage());

        } finally {
            // CIERRE DE RECURSOS
            try {
                if (colFormacion != null) {
                    colFormacion.close();
                }
                if (col != null) {
                    col.close();
                }
                System.out.println("Conexiones cerradas correctamente");
            } catch (XMLDBException e) {
                System.err.println("Error al cerrar la conexión");
            }
        }
    }
}
