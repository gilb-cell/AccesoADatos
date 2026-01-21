package org.example;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

/**
 *  Gilberto
 *  UT6 - Tarea 3
 *  Acceso a Datos
 * Gestión de Documentos en eXistDB usando XML:DB
 */
public class GestionDocumentosExist {

    // Datos de conexión
    private static final String DRIVER = "org.exist.xmldb.DatabaseImpl";
    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/Formacion/Profesores";
    private static final String USER = "admin";
    private static final String PASS = "";

    //Variables para cambiar el nombre a los documentos creados
    private static final String nombreDocument = "Gilberto.xml";
    private static final String nombreDocument2 = "AlfonsoDiaz.xml";

    public static void main(String[] args) {

        Collection col = null;
      //  Collection colProfesores = null;

        try {
            // CARGAR Y REGISTRAR EL DRIVER
            Class<?> cl = Class.forName(DRIVER);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);

            //OBTENER LA COLECCIÓN RAÍZ (/db)
            col = DatabaseManager.getCollection(URI, USER, PASS);

            if (col == null) {
                System.out.println("No se pudo acceder a la colección raíz /db");
                return;
            }

            System.out.println("Conectado correctamente a eXistDB\n");


            //Nos ahorramos las gestion de las colleciones porque en la URI hemos
            //accedido hasta la coleccion que vamos a crear los documentos, (Profesores)


            // Crear recurso vacío en memoria y con if manejamos errores
            Resource res1 = col.createResource("Gilberto.xml", "XMLResource");
            if (res1 == null) {
                System.out.println("No se pudo crear" + nombreDocument);
                return;
            }
            System.out.println("Recurso vacio " +nombreDocument + " creado correctamente en eXistDB");

            Resource res2 = col.createResource("AlfonsoDiaz.xml", "XMLResource");
            if (res2 == null) {
                System.out.println("No se pudo crear" + nombreDocument2);
                return;
            }
            System.out.println("Recurso vacio " + nombreDocument2 + " creado correctamente en eXistDB \n");

            //DEFINIMOS LOS DOCUMENTOS XML

            String contenidoXML1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<profesor id=\"3\">" + "<nombre>Gilberto</nombre>" +
                   " <dni>27654789</dni>" +
                  "  <email>gil.power@dominiox.es</email>" +
            "</profesor>";

            String contenidoXML2 =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<profesor id=\"4\">" + "<nombre>Alfonso Diaz</nombre>" +
                    "<dni>23345457</dni>" +
            "  <email>diaz.alfons@dominiox.es</email>" +
            "</profesor>";



            //CREAMOS o ASIGNAR LOS DOCUMENTOS XML el contenido definido

          res1.setContent(contenidoXML1);
          res2.setContent(contenidoXML2);
          System.out.println("Contenido de los documentos creado correctamente en eXistDB \n");

            //GUARDAR EN LA COLLECION
            col.storeResource(res1);
            col.storeResource(res2);
            System.out.println("Contenido guardado en la coleccion correctamente\n");



            //ELIMINAR LA COLECCIÓN
            col.removeResource(res1);
            System.out.println(" Documento " + nombreDocument + " eliminado correctamente");
            col.removeResource(res2);
            System.out.println(" Documento " + nombreDocument2 + " eliminado correctamente\n");

        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el driver de eXistDB");

        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("Error al crear la instancia del driver");

        } catch (XMLDBException e) {
            System.err.println("Error de eXistDB: " + e.getMessage());

        } finally {
            // CIERRE DE RECURSOS
            try {
                /*if (colProfesores != null) {
                    colProfesores.close();
                }*/
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
