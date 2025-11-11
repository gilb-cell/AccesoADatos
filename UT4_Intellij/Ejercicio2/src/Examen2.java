import javax.swing.*;
import java.sql.*;

/**
 * @author Gilberto
 * @since 06/11/2025
 * @dato Examen UT3
 */

public class Examen2 {

    // Definimos los datos de conexion
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";


    public static void main(String[] args) {

        //Sentencia con SELECT
        try (
              Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
              Statement s = c.createStatement()

              // Sentencia SQL para hacer una consulta a la base de datos y lo almacenamos en un objeto resultSet
        ) { ResultSet resultSet = s.executeQuery("SELECT NUMERO_LLAMADO FROM llamadas_emitidas WHERE CODIGO_LLAMADA = 1000017");

            while (resultSet.next()) {
                int numero = resultSet.getInt("NUMERO_LLAMADO");

                System.out.println("El numero llamado es: " + numero);
            }
        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }



        //Sentencia con PREPAREDSTATEMENT

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int codigoLlamada =1000054 ;

        try {
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);


            String sql = "SELECT DURACION_LLAMADA FROM llamadas_emitidas WHERE CODIGO_LLAMADA = ?";


            preparedStatement = connection.prepareStatement(sql);



            preparedStatement.setInt(1, codigoLlamada);


            System.out.println("Ejecutando consulta: " + preparedStatement);
            ResultSet resultSet =  preparedStatement.executeQuery();

            System.out.println("Procesando resultado......");

            while (resultSet.next()) {

                int duracionLlamada = resultSet.getInt("DURACION_LLAMADA");


                //MOstramos informacion
                System.out.println("****** DURACIÓN ENCONTRADA:****** ");
                System.out.println(" La duración de la llamada es: " + duracionLlamada);


            }

        } catch (SQLException e) {
            muestraErrorSQL(e);
            System.err.println("Código de error SQL específico: " + e.getErrorCode());
        }catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        } finally {

            // Cerramos conexion
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }


        // Resultset

        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {

            c.setAutoCommit(false);


            try (Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                ResultSet rs = s.executeQuery("SELECT CODIGO_LLAMADA, DURACION_LLAMADA FROM llamadas_emitidas ");

                
                int filasModificadas = 0;
                while (rs.next()) {
                    String duracionActual = rs.getString("DURACION_LLAMADA");

                    if (duracionActual == null) {

                        rs.updateString("DURACION_LLAMADA","111");
                        rs.updateRow();
                        filasModificadas++;
                    }

                }

                //Confirmamos la transaccion
                c.commit();


                //Mostramos el numero de filas con el cuadro de dialogo
                JOptionPane.showMessageDialog(null, filasModificadas + " filas modificadas en la tabla llamadas emitidas",
                        "Resultado de modificacion", JOptionPane.INFORMATION_MESSAGE);


            }

        } catch (SQLException e) {


            // c.rollback(); dentro del catch y deel try

            muestraErrorSQL(e);
            System.err.println("Código de error SQL específico: " + e.getErrorCode());
        } catch (Exception e) {
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
