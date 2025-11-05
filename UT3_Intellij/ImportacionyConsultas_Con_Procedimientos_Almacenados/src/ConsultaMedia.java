import java.sql.*;

public class ConsultaMedia {

    //Datos de conexion
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {



        //variables para calcular la media
        double suma = 0.0;
        int contador = 0;


        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
              Statement s = c.createStatement()

              // Sentencia SQL para hacer una consulta a la base de datos y la hemos almacenado en un objeto resultSet
        ) { ResultSet resultSet = s.executeQuery("SELECT Precipitacion FROM datos_climaticos");


            //Filas resultados
            while (resultSet.next()) {
                //calcular la media
                suma += resultSet.getDouble("Precipitacion");
                contador++;
            }
            //Mostrar los resultados de ña media `por consola
            System.out.println( "La media de precipitacion en Octubre es " +  suma / contador );

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
