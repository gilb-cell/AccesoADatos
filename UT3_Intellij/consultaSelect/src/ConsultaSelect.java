

/**
 * @author Gilberto
 * @since 20/10/2025
 * @dato Tarea 3 UT3
 */

import java.sql.*;

public class ConsultaSelect {

    // Definimos las constantes para los datos de conexion
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {

        // Se omiten las variables explícitas de conexión

        try ( //bloque try-with-resources (tambien cierra automaticamente la conexion)
              // para establecer la conexión con la base de datos y creamos un objeto Statement.
              Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
              Statement s = c.createStatement()

              // Sentencia SQL para hacer una consulta a la base de datos y la hemos almacenado en un objeto resultSet
        ) { ResultSet resultSet = s.executeQuery("SELECT CODIGO_AGENTE, NOMBRE_AGENTE, FRASE_CLAVE FROM agentes;");


          while (resultSet.next()) {
            //Definimos el tipo de dato y nombre de la consulta echa
              String nombre = resultSet.getString("NOMBRE_AGENTE");
              int codigo = resultSet.getInt("CODIGO_AGENTE");
              String fraseClave = resultSet.getString("FRASE_CLAVE");
              //Imprimimos por pantalla los resultados de la consulta, llamando a las variables
              System.out.println(nombre + " " + codigo + " " + fraseClave);
          }
        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (Exception e) { //Capturamos cualquier otra excepcion
            e.printStackTrace(System.err);
        }
    }


    //método llamado muestraErrorSQL que reciba un objeto SQLException y muestre en la consola
    private static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage()); //Mensaje de error
        System.err.println("SQL State: " + e.getSQLState()); //El estado del sql
        System.err.println("Vendor Error Code: " + e.getErrorCode()); //El código de error del proveedor
    }
    }


