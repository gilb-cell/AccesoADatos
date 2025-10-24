


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

/**
 * @author Gilberto
 * @since 21/10/2025
 * @dato Tarea 4 UT3
 */

public class ConsultaPREPARED {

    // Definimos las constantes para los datos de conexion
    //Tener en mente activar el .jar para la API en el apartado de project Structure
   // public static String DB = "ut3";
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
  // int codigoAgenteBuscar = 0;

    public static void main(String[] args) {
        //Los iniciamos en null para poder acceder a ellos en el bloque try
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int codigoAgenteBuscar = 7;

        try {
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // 2º Definir la sentencia o consulta SQL con marcadores de posición
            String sql = "SELECT CODIGO_AGENTE, NOMBRE_AGENTE, FRASE_CLAVE FROM agentes WHERE CODIGO_AGENTE = ?";

           //3º Usamos connection.prepareStatement(sql) para preparar la consulta y creamos el objeto.
            preparedStatement = connection.prepareStatement(sql);


          //4º asignar valores a los parametros
            preparedStatement.setInt(1, codigoAgenteBuscar);

            //5º ejecutar la consulta
            System.out.println("Ejecutando consulta: " + preparedStatement); //Mostramos la consulta (el ? se vera como 7)
            ResultSet resultSet =  preparedStatement.executeQuery();

            System.out.println("Procesando resultado......");

            while (resultSet.next()) {

                int codigoAgente = resultSet.getInt("CODIGO_AGENTE");
                String nombreAgente = resultSet.getString("NOMBRE_AGENTE");
                String fraseClave = resultSet.getString("FRASE_CLAVE");

                //MOstramos la informacion recuperada por pantalla
                System.out.println(" AGENTE ENCONTRADO: ");
                System.out.println("  Codigo de agente: " + codigoAgente);
                System.out.println("  Nombre de agente: " + nombreAgente);
                System.out.println("  Frase clave: " + fraseClave);

            }

        } catch (SQLException e) {
            muestraErrorSQL(e);
        }catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        } finally {

            // Cerrar el PreparedStatement y la conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }

    //método llamado muestraErrorSQL que reciba un objeto SQLException y muestre en la consola
    private static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage()); //Mensaje de error
        System.err.println("SQL State: " + e.getSQLState()); //El estado del sql
        System.err.println("Vendor Error Code: " + e.getErrorCode()); //El código de error del proveedor
    }
}