import javax.swing.*;
import java.sql.*;

/**
 * @author Gilberto
 * @since 23/10/2025
 * @dato TA6 UT3
 */

public class ModificarClientesResultSet {

    // Datos de conexión
    private static final String urlConnection = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";


    public static void main(String[] args) {

// Se omite la declaración explícita de variables para los datos de conexión
// 	Conectar a la base de datos y crear Statement, dentro del parentesis, asi se cierra automatico

        try (Connection c = DriverManager.getConnection(urlConnection, USER, PASSWORD)) {
            //Deshabilitar auto-commit para manejo manual de transsacciones
            c.setAutoCommit(false);

            //Usamos otro try para el Statement aunque se podria incluir en el mismo bloque
            try (Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                ResultSet rs = s.executeQuery("SELECT DNI, APELLIDOS, CP FROM CLIENTES ");

                //Inicializamos el contador para saber cuantas filas son modificadas
                int filasModificadas = 0;
                while (rs.next()) {
                    String cpActual = rs.getString("CP");

                    if (cpActual == null) {
                        //1 actualizacion en el buffer del ResultSet
                        rs.updateString("CP", " 00000 ");
                        rs.updateRow();
                        filasModificadas++;
                    }

                }

                //Confirmamos la transaccion
                c.commit();


                //Mostramos el numero de filas con el cuadro de dialogo
                JOptionPane.showMessageDialog(null, filasModificadas + " filas modificadas en la tabla clientes",
                        "Resultado de modificacion", JOptionPane.INFORMATION_MESSAGE);


            }

        } catch (SQLException e) {

            //(Opcional) Si algo falla deberiamos deshacer la transaccion
            //para evitar cambios parciales, usariamos
            // c.rollback(); dentro del catch y deel try

            muestraErrorSQL(e);
            System.err.println("Código de error SQL específico: " + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    //Metodo para gestionar los errores SQL
    private static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage());
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Vendor Error Code: " + e.getErrorCode());
    }
}