import jakarta.persistence.*;
import java.util.Scanner;

/**
 * Gilberto Tarea2Ut5
 */

public class inventarioODBapp {

            public static void main(String[] args) {

                try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                        "C:\\Users\\AlumnoDAM2\\Desktop\\Acceso_Datos\\ObjectDB\\objectdb-2.9.4\\objectdb-2.9.4\\db\\productos.odb");
                     EntityManager em = emf.createEntityManager();
                     Scanner sc = new Scanner(System.in)) {

                    boolean salir = false;
                    while (!salir) {
                        System.out.println("\n MENÚ INVENTARIO ");
                        System.out.println("1. Agregar nuevo producto");
                        System.out.println("2. Salir");
                        System.out.print("Elige una opción: ");
                        String opcion = sc.nextLine();

                        switch (opcion) {
                            case "1":
                                System.out.print("Nombre del producto: ");
                                String nombre = sc.nextLine();

                                System.out.print("Precio del producto: ");
                                double precio;
                                try {
                                    precio = Double.parseDouble(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Precio inválido. Intenta de nuevo.");
                                    break;
                                }

                                System.out.print("¿Está en oferta? (true/false): ");
                                boolean oferta;
                                try {
                                    oferta = Boolean.parseBoolean(sc.nextLine());
                                } catch (Exception e) {
                                    System.out.println("Valor inválido. Intenta de nuevo.");
                                    break;
                                }

                                Producto nuevoProducto = new Producto(nombre, precio, oferta);
                                EntityTransaction tx = em.getTransaction();

                                try {
                                    tx.begin();
                                    em.persist(nuevoProducto);
                                    tx.commit();
                                    System.out.println("Producto agregado correctamente.");
                                } catch (Exception e) {
                                    if (tx.isActive()) {
                                        tx.rollback(); // Rollback si algo sale mal
                                        System.out.println("Transacción revertida. Error al guardar el producto.");
                                    }
                                    System.out.println("Detalles del error: " + e.getMessage());
                                }
                                break;

                            case "2":
                                salir = true;
                                break;

                            default:
                                System.out.println("Opción no válida, intenta de nuevo.");
                                break;
                        }
                    }

                } catch (Exception e) {
                    System.err.println("Error de conexión con la base de datos: " + e.getMessage());
                }

                System.out.println("Programa finalizado.");
            }
        }






