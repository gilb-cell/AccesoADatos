import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Gilberto
 * @since 06/11/2025
 * @dato Examen UT3
 */

public class Examen1 {

    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";


    public static void main(String[] args) {

        try (
              Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
              Statement s = c.createStatement()
        ) {
            // Sentencia SQL para crear la tabla EXAMEN
            s.execute("CREATE TABLE EXAMEN (" +
                    "DNI CHAR(9) NOT NULL, " +
                    "APELLIDOS VARCHAR(32) NOT NULL, " +
                    "NOTAS DECIMAL(5,2) NOT NULL, " +
                    "PRIMARY KEY (DNI))");
            System.out.println("La tabla Examen ha sido creada exitosamente.");
        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        //Insertamos datos en la tabla

        try (  Connection conec = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement st = conec.createStatement()) {
            //nFil variable para almacenar el numero de filas afectadas
            int nFil = st.executeUpdate(
                    "INSERT INTO EXAMEN (DNI, APELLIDOS, NOTAS) VALUES " +
                            "('77722529D', 'Gandia', '7.2');"
            );

            JOptionPane.showMessageDialog(null, nFil + "fila insertada" ,
                    "Resultado de insercion", JOptionPane.INFORMATION_MESSAGE );

            System.out.println(nFil + " Fila insertada. Correctamente");

        } catch (SQLException e) {
            muestraErrorSQL(e);
            System.err.println("Código de error SQL específico: " + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }


    private static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage());
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Vendor Error Code: " + e.getErrorCode());
    }
}


