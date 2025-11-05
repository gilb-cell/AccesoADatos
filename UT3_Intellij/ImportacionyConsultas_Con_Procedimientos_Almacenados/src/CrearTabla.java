import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTabla {

        // Datos de conexión
        private static final String urlConnection = "jdbc:mysql://localhost:3306/ut3";
        private static final String USER = "root";
        private static final String PASSWORD = "123456";

        public static void main(String[] args) {
            // Se omiten las variables explícitas de conexión

            try ( //bloque try-with-resources (tambien cierra automaticamente la conexion)
                  // para establecer la conexión con la base de datos y creamos un objeto Statement.
                  Connection c = DriverManager.getConnection(urlConnection, USER, PASSWORD);
                  Statement s = c.createStatement()
            ) {
                // Sentencia SQL para crear la tabla CLIENTES
                s.execute("CREATE TABLE datos_climaticos (" +
                        "Provincia VARCHAR(15) NOT NULL, " +
                        "Estacion VARCHAR(15) NOT NULL, " +
                        "Fecha VARCHAR(15), " +
                        "Temperatura DECIMAL(5, 2), " + // 5 DIGITOS Y DOS DE ELLOS DECIMALES
                        "Humedad DECIMAL(5, 2), " +
                        "Precipitacion DECIMAL(4, 1)) ");
                    //    "PRIMARY KEY (Fecha))"); // podemos no poner clase primary key
                System.out.println("Tabla CLIENTES creada exitosamente.");

            } catch (SQLException e) {
                muestraErrorSQL(e);
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

