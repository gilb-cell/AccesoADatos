
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainPersonajes {

    public static void main(String[] args) {

        EntityTransaction tx = null;

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "C:/Users/AlumnoDAM2/Desktop/Acceso_Datos/ObjectDB/objectdb-2.9.4/objectdb-2.9.4/db/personajesJuego.odb");
             EntityManager em = emf.createEntityManager();) {

            tx = em.getTransaction();

            // Personajes
            //    List<Personaje> personajesLista = new ArrayList<>();
            Personaje personaje1 = new Personaje("Arthas", "Guerrero", 10);
            Personaje personaje2 = new Personaje("Jaina", "Mago", 12);
            Personaje personaje3 = new Personaje("Sylvanas", "Arquero", 8);
            Personaje personaje4 = new Personaje("Thrall", "Chaman", 14);
            Personaje personaje5 = new Personaje("Anduin", "Sacerdote", 9);


            // Persistencia segura de cada objeto
            tx.begin();
            em.persist(personaje1);
            em.persist(personaje2);
            em.persist(personaje3);
            em.persist(personaje4);
            em.persist(personaje5);
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
