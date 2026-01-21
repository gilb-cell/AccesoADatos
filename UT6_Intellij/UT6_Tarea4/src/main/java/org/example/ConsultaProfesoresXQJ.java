package org.example;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import java.lang.reflect.InvocationTargetException;

/**
 *  Gilberto
 *  UT6 - Tarea 4
 *  Acceso a Datos
 * Consultas XQuery en eXistDB usando API XQJ
 */
public class ConsultaProfesoresXQJ {

    private static final String DRIVER = "org.exist.xmldb.DatabaseImpl";
    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/Formacion/Profesores";
    private static final String USER = "admin";
    private static final String PASS = "";

    public static void main( String[] args ){

        //iMPORTANTE EL NULL PARA QUE SEA VISIBLE EN EL finally
        XQConnection conn = null;
        try {

            //CARGAMOS EL DRIVER Y LOS DATOS DE CONEXION
            XQDataSource xqs = (XQDataSource)
                    Class.forName("net.xqj.exist.ExistXQDataSource").getDeclaredConstructor().newInstance();
            xqs.setProperty("serverName", "localhost");
            xqs.setProperty("port", "8080");
            xqs.setProperty("user", "admin");
            xqs.setProperty("password", "");

            //OBTENEMOS LA CONEXION
            conn = xqs.getConnection();

            //CREAR EXPRESION XQUERY
            XQExpression expr = conn.createExpression();

            //UNA SOLA QUERY (UNA FORMA MAS LIMPIA Y BREVE)
         /*   String xquery =
                    "for $p in doc('/db/Formacion/Profesores/JesusLozano.xml')/profesor " +
                            "return concat(" +
                            "'Nombre: ', $p/nombre, ', DNI: ', $p/dni, ', Email: ', $p/email" +
                            ")";
            XQResultSequence rs = expr.executeQuery(xquery); */

            //Ejecutar CONSULTA XQUERY
            String xquery = "doc('/db/Formacion/Profesores/JesusLozano.xml')/profesor/nombre ";
            XQResultSequence rs = expr.executeQuery(xquery);

            String xquery2 = "doc('/db/Formacion/Profesores/JesusLozano.xml')/profesor/dni ";
            XQResultSequence rs2 = expr.executeQuery(xquery2);

            String xquery3 = "doc('/db/Formacion/Profesores/JesusLozano.xml')/profesor/email ";
            XQResultSequence rs3 = expr.executeQuery(xquery3);

            //PROCESAMOS LOS RESULTADOS DE LA xQUERY
            while (rs.next()) {
                String valor = rs.getItemAsString(null);
                System.out.println("Profesor: " + valor);
            }

           while (rs2.next()) {
                String valor = rs2.getItemAsString(null);
                System.out.println("Profesor: " + valor);
            }

            while (rs3.next()) {
                String valor = rs3.getItemAsString(null);
                System.out.println("Profesor: " + valor);
            }

            //MANEJAMOS TODAS LAS EXCEPCIONES
        }catch (ClassNotFoundException | XQException e) {
            System.out.println("Falta el JAR del driver " + e.getMessage());
        }catch (NoSuchMethodException e) {
            System.out.println("El driver no tiene constructor sin parametros " + e.getMessage());
        }catch (InstantiationException e) {
            System.out.println("No se puede crear una instancia del driver " + e.getMessage());
        }catch(IllegalAccessException e) {
            System.out.println("Sin permisos de acceso al constructor " + e.getMessage());
        }catch (InvocationTargetException e){
            System.out.println("Error en la ejecucion del constructor " + e.getMessage());
        } finally {
            try{ //CERRAMOS RECURSOS

                if (conn != null) {
                    conn.close();
                }
            }catch(Exception e){}
        }

    }
}
