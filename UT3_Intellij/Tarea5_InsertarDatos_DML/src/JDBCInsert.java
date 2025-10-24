import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * @author Gilberto
 * @since 23/10/2025
 * @dato TA5 UT3
 */

public class JDBCInsert {

    // Datos de conexión
    private static final String urlConnection = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";


    public static void main(String[] args) {

// Se omite la declaración explícita de variables para los datos de conexión
// 	Conectar a la base de datos y crear Statement, dentro del parentesis, asi se cierra automatico

        try (
                Connection c = DriverManager.getConnection(urlConnection, USER, PASSWORD);
                Statement s = c.createStatement() //objeto Statement
        ) {
// Ejecución de sentencias INSERT con múltiples valores
            //nFil es una variable para almacenar el numero de filas afectadas
            int nFil = s.executeUpdate( //para ejecutar la sentencia
                    "INSERT INTO CLIENTES (DNI, APELLIDOS, CP) VALUES " +
                            "('78901234X', 'Gandia', '30520'), " +
                            "('89012345E', 'Sanchez', '30520'), " +
                            "('56789012B', 'Hernandez', '29730'), " +
                            "('09876543K', 'Garcia', '30528');"
            );

            //Mostramos el resultado en vez de x consola lo hacemos por interfaz con javaSwing
            //mostrar el resultado en un JOptionPane (un cuadro de diálogo)
            //usaremos 	JOptionPane.showMessageDialog
            JOptionPane.showMessageDialog(null, nFil + "filas insertadas" ,
                    "Resultado de insercion", JOptionPane.INFORMATION_MESSAGE );

            System.out.println(nFil + " Filas insertadas.");
        } catch (SQLException e) {
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

