import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Gilberto
 * @since 30/10/2025
 * @dato TA7 UT3
 */

public class EjecutarProcedimiento {

    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        ejecutarProcedimientoPrecipitacionTotal("Algemesí");

    }

    public static void ejecutarProcedimientoPrecipitacionTotal(String estacion) {

        double totalPrecipitacion = 0.0;
        String sql = "{CALL CalcularPrecipitacionTotal(?, ?)}";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        CallableStatement callableStatement = connection.prepareCall(sql);) {


// Asignar valores a los parámetros de entrada

            callableStatement.setString(1, estacion); // ID del producto
            callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL); // PARAMETRO DE SALIDA

// Ejecutar el procedimiento
            callableStatement.execute();

            //oBTENER EL RESULTADO DEL PARAMETRO DE SALIDA
            totalPrecipitacion = callableStatement.getDouble(2);

            //Mostrar el resultado en un cuadro de dialogo
            JOptionPane.showMessageDialog(null, "Precipitacion total en " + estacion +
                    "durante octubre:  " + totalPrecipitacion + "mm");

        } catch (SQLException e) {
            muestraErrorSQL(e);
        }

    }

    //método llamado muestraErrorSQL que reciba un objeto SQLException y muestre en la consola
    private static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage()); //Mensaje de error
        System.err.println("SQL State: " + e.getSQLState()); //El estado del sql
        System.err.println("Vendor Error Code: " + e.getErrorCode()); //El código de error del proveedor
    }
}

