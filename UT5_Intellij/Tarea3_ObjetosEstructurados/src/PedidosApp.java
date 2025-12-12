


import jakarta.persistence.*;
import java.util.*;

/**
 * Gilberto
 * Tarea3 Ut5
 */

public class PedidosApp {

    public static void main(String[] args) {

        EntityTransaction tx = null;

        try (Scanner sc = new Scanner(System.in);
             EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                     "C:/Users/AlumnoDAM2/Desktop/Acceso_Datos/ObjectDB/objectdb-2.9.4/objectdb-2.9.4/db/pedidosODB.odb"
             ); EntityManager em = emf.createEntityManager();) {


            tx = em.getTransaction();

            // Productos
            List<Producto> catalogo = new ArrayList<>();
            catalogo.add(new Producto("PlayStation 5", 499.99, 10));
            catalogo.add(new Producto("Teclado Gaming", 45.50, 20));
            catalogo.add(new Producto("Ratón Gamer", 30.99, 15));


            System.out.println("=== NUEVO PEDIDO ===");
            System.out.print("Código del pedido: ");
            String codigo = sc.nextLine();

            System.out.print("ID del cliente: ");
            Long idCliente = sc.nextLong();

            List<Producto> seleccionados = new ArrayList<>();

            System.out.println("--- PRODUCTOS DISPONIBLES ---");
            for (int i = 0; i < catalogo.size(); i++) {
                System.out.println(i + " - " + catalogo.get(i));
            }

            System.out.println("Introduce posiciones de productos (-1 para terminar):");

            while (true) {
                int opcion = sc.nextInt();
                if (opcion == -1) break;
                if (opcion >= 0 && opcion < catalogo.size()) {
                    seleccionados.add(catalogo.get(opcion));
                }
            }

            Pedido pedido = new Pedido(codigo, idCliente, seleccionados);
            // Persistencia segura
            tx.begin();
            em.persist(pedido);
            tx.commit();

            System.out.println("Pedido guardado correctamente.");

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (tx.isActive()) {
                tx.rollback(); //Si falla algo durante la transsacion, poder volver al principio.
            }
        }
    }
}