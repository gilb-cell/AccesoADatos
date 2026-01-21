import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMisiones {

    public static void main(String[] args) {

        EntityTransaction tx = null;

        try (Scanner sc = new Scanner(System.in);  EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "C:/Users/AlumnoDAM2/Desktop/Acceso_Datos/ObjectDB/objectdb-2.9.4/objectdb-2.9.4/db/misionesJuego.odb");
             EntityManager em = emf.createEntityManager();) {

            tx = em.getTransaction();

            // Personajes
            //    List<Personaje> personajesLista = new ArrayList<>();

            Mision mision = new Mision("Rescartar al rey", List.of());
            Mision mision1 = new Mision("Derrotar al dragon", List.of());

            Personaje personaje1 = new Personaje("Arthas", "Guerrero", 10);
            Personaje personaje2 = new Personaje("Jaina", "Mago", 12);
            Personaje personaje3 = new Personaje("Sylvanas", "Arquero", 8);
            Personaje personaje4 = new Personaje("Thrall", "Chaman", 14);
            Personaje personaje5 = new Personaje("Anduin", "Sacerdote", 9);


            System.out.println("=== NUEVO PEDIDO ===");
            System.out.print("Código del pedido: ");
            String codigo = sc.nextLine();

            System.out.print("ID del cliente: ");
            Long idCliente = sc.nextLong();

            List<Personaje> seleccionados = new ArrayList<>();

            System.out.println("--- PRODUCTOS DISPONIBLES ---");
            for (int i = 0; i < mision.size(); i++) {
                System.out.println(i + " - " + misiones.get(i));
            }

            System.out.println("Introduce posiciones de productos (-1 para terminar):");

            while (true) {
                int opcion = sc.nextInt();
                if (opcion == -1) break;
                if (opcion >= 0 && opcion < misiones.size()) {
                    seleccionados.add(misiones.get(opcion));
                }
            }

            Mision mision = new Mision();



            // Persistencia segura de cada objeto
            tx.begin();

            tx.commit();

            System.out.println("Personajes guardados correctamente.");


            try {

                String consulta = "SELECT p FROM Personaje p WHERE p.ID = 4 ";
                /* TypedQuery para consultas, mientras que Query es más adecuada para actualizaciones y eliminaciones.*/
                TypedQuery<Personaje> q = em.createQuery(consulta, Personaje.class);

                List<Personaje> personajes = q.getResultList();

                if (personajes.isEmpty()) {
                    System.out.println("No se puede encontrar al personaje");
                } else {
                    System.out.println("Nivel del personaje: "  + personajes.get(0).getNivel());

                    for (Personaje personaje : personajes) {
                        System.out.println(personaje);
                    }
                }

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
                if (tx.isActive()) {
                    tx.rollback(); //Si falla algo durante la transsacion, poder volver al principio.
                }


            }
        }
    }
    }
