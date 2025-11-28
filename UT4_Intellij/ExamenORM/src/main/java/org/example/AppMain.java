package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AppMain {

    // EntityManagerFactory: Fabrica que crea EntityManagers (UNA por aplicación)
    // Esto lee el fichero 'persistence.xml' y busca la unidad de persistencia "default"
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    /**
     * ═══════════════════════════════════════════════════════════════════
     * MÉTODO MAIN - PUNTO DE ENTRADA DEL PROGRAMA
     * ═══════════════════════════════════════════════════════════════════
     */
    public static void main(String[] args) {
        System.out.println("=== INICIO DE LA APLICACIÓN DE GESTIÓN DE CLIENTES PARA EL EXAMEN ===\n");

        try {

            // ═════════════════════════════════════════════════════════
            // APARTADO A: INSERTAR UN NUEVO CLIENTE
            // ═════════════════════════════════════════════════════════
           InsertarNuevoCliente();

            // ═════════════════════════════════════════════════════════
            // APARTADO B: CONSULTAR UN CLIENTE EXISTENTE
            // ═════════════════════════════════════════════════════════
           ConsultarClienteExistente();

            // ═════════════════════════════════════════════════════════
            // APARTADO C: MODIFICAR UN CLIENTE EXISTENTE
            // ═════════════════════════════════════════════════════════
            ModificarClienteExistente();

            // ═════════════════════════════════════════════════════════
            // APARTADO D: ELIMINAR UN CLIENTE EXISTENTE
            // ═════════════════════════════════════════════════════════
            EliminarClienteExistente("96878083K");

        }catch (Exception e){
            System.err.println("Mensaje: " + e.getMessage());
            e.printStackTrace();
        }finally {
            // Cerramos la factoría al finalizar la aplicación
            emf.close();
            System.out.println("\n=== FIN DE LA APLICACIÓN ===");

        }

    }

    /**
     * ═══════════════════════════════════════════════════════════════════
     * IMPLEMENTACIÓN DE LOS MÉTODOS DEL EXAMEN
     * ═══════════════════════════════════════════════════════════════════
     */

    private static void InsertarNuevoCliente() {
        // TODO: Implementar apartado A
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = null;

      try {
        tx = em.getTransaction();
        tx.begin();
        System.out.println("Se ha inciado la transaccion");

        Cliente nuevoCliente = new Cliente();

          // configuramos al nuevo cliente
          nuevoCliente.setDni("96878083K");
          nuevoCliente.setApellidos("Olivares");
          nuevoCliente.setCp("30510");

        em.persist(nuevoCliente);
        System.out.println("Ejecutandose insert Cliente");

        tx.commit();
        System.out.println("Ejecutando el commit, Cliente insertado en BD");


      }catch (Exception e){
          if(tx!=null) {
              tx.rollback();
              System.err.println("La Transaccion fue invertida : " + e.getMessage());
          }
          System.err.println("Causa: "  + e.getCause());
      }finally {
          em.close();
          System.out.println("El EntityManager se ha cerrado");
      }

    }

    private static void ConsultarClienteExistente() {
        // TODO: Implementar apartado B
        EntityManager em = emf.createEntityManager();

        try  {
            String dniBuscar = "96878083K";

            Cliente clienteBuscado = em.find(Cliente.class, dniBuscar);

            if(clienteBuscado!=null){
                System.out.println("El DNI del cliente es: " + clienteBuscado.getDni());
                System.out.println("Los apellidos del cliente es: " + clienteBuscado.getApellidos());
                System.out.println("El codigo postal del cliente es: " + clienteBuscado.getCp());
            }else{
                System.out.println("El DNI buscado no corresponde con ningun cliente");


        }

            }catch (Exception e){
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }

    }

    private static void ModificarClienteExistente() {
        // TODO: Implementar apartado C

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;


        try {
            tx = em.getTransaction();
            tx.begin();
            System.out.println("Se inicio la transaccion");

            String cpModificar = "30520";

            Cliente clienteModificado = em.find(Cliente.class, cpModificar);

            if(clienteModificado!=null){
                String cpAnterior = clienteModificado.getCp();
                System.out.println("El codigo postal anterior es: " + cpAnterior);


                String cpNuevo = clienteModificado.getCp();
                clienteModificado.setCp(cpNuevo);
                System.out.println("El codigo postal nuevo es: " + cpNuevo);

                tx.commit();
                System.out.println("Registro actualizado en la BD");

            }else{
                System.out.println("El Codigo postal no existe " );
            }

        }catch (Exception e){
            if(tx != null && tx.isActive()){
                tx.rollback();
                System.err.println("Rollback ejecutandose");
            }
            System.err.println("Causa: " + e.getMessage());

        }finally {
            em.close();
            System.out.println("El EntityManager se ha cerrado");
        }

    }

    private static void EliminarClienteExistente(String DNI) {
        // TODO: Implementar apartado D


        //Eliminar de la base de datos el cliente con DNI 96878083K

        System.out.println("Eliminando el usuario con DNI: " + DNI );

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;

        try{
            tx = em.getTransaction();
            tx.begin();
            System.out.println("Transaccion iniciada");


            //1 Encontrar registro en tabla y traertelo a java "Recuperar objeto" (cargar oibjeto)
            System.out.println("Recuperando el objeto con find()");
            Cliente clienteBuscado = em.find(Cliente.class, DNI);


            if(clienteBuscado != null) {
                System.out.println("Registro recuperado en estado persistente");


                System.out.println("Ejecutando remove");
                em.remove(clienteBuscado);

                tx.commit();
                System.out.println("Commit ejecutado");
            }else {
                System.out.println("El registro no existe");
            }


        }catch(Exception e){
            if(tx != null && tx.isActive()){
                tx.rollback();
                System.err.println("Rollback ejecutandose");
            }
            System.err.println("Causa: " + e.getMessage());
        }finally {
            em.close();
            System.out.println("EntityManager cerrado.");
        }


    }

}
