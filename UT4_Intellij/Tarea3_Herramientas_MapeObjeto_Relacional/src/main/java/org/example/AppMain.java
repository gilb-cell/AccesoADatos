package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Gilberto
 */

public class AppMain {

    //EntityManagerFactory: fabruca q crea EntityManagers (una por aplicacion)AppMain
  static String persistenceUnitName = "default"; //nombre de la persistence unit en el archivo persistence
   static EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);

    public static void main(String[] args) {

      //  crearNuevaLlamada();

       // consultarRegistroExistente();

      //  modificarRegistroExistente();

      //  eliminarRegistro(1000072);

     //  gestionEstados();

    }

    //Método para crear una llamada nueva
    public static Integer crearNuevaLlamada() {

        //EntityManager: gestor JPA que maneja las operaciones con la BD (uno por operacion)
        EntityManager em = emf.createEntityManager();

        //variable para controlar la transsacion
        EntityTransaction tx = null;

        //variable para guardar el ID generado
        Integer codigoGenerado = null;

        //declaramos la simllamante
        int simLlamante = 617478396;

        //bloque try-catch-finally
            //dentro de try:
        try{
           //1 iniciamos transsacion
            em.getTransaction().begin();
            System.out.println("Transsacion iniciada");

            //2 creamos el objeto en estado transitorio (es posible)
            LlamadasEmitida nuevaLlamada = new LlamadasEmitida();


            //necesitamos primero obtener la tarjeta telefonica  (FOREING kEY)

            //SUPONEMOS q existe una tarjeta con NUMERO_SIM = 617478396
            //con find() hacemos busqueda  //DESDE AQUI SE HIZO ESTE CACHO DE CODIGO XQ SE COMPLICO CON LA CLAVE FORANEA
            TarjetasTelefonica tarjeta = em.find(TarjetasTelefonica.class, simLlamante);

            if(tarjeta == null){
                //Si no existe, creamos una tarjeta de ejemplo
                System.out.println("La tarjeta XXX no existe. Creando una de ejemplo....");
                tarjeta = new TarjetasTelefonica();
                tarjeta.setId(simLlamante);

                //La tarjeta necesita un agente (foreing key)
                Agente agente = em.find(Agente.class, 9);

                if(agente == null){
                    System.out.println("El agente con ID 9 no existe. Creando una de ejemplo....");
                    agente = new Agente();
                    agente.setId(9);
                    agente.setNombreAgente("Agente Demo");
                    agente.setFraseClave("Clave123");
                    em.persist(agente);

                }
                tarjeta.setCodigoAgenteAsociado(agente);
                em.persist(tarjeta);  //HASTA AQUI LA COMPLICACION DE LA CLAVE FORANEA

                //Ahora si, configuramos la nueva llamada
                //3 configuramos  la nueva llamada
                nuevaLlamada.setNumeroLlamado(987654321);
                nuevaLlamada.setDuracionLlamada(300);
                nuevaLlamada.setImporteLlamada(12.50f);
                nuevaLlamada.setSimLlamante(tarjeta);

                nuevaLlamada.setId(1000073); //Es el ultimo de nuestra base de datos
                System.out.println("Objeto creado em estado TRANSITORIO");

                //4 Usamos persist() para ese objeto
                em.persist(nuevaLlamada);
                System.out.println("persist() ejecutando -> INSERT realizado en BD");

                //5 hacemos commit
                tx.commit();
                System.out.println("commit() ejecutando -> INSERT realizado en BD");

                //Ahora ya tiene su ID asignado
                codigoGenerado = nuevaLlamada.getId();

            }

        }catch(Exception e){
            if(tx != null){
                tx.rollback();
                System.err.println("Error: transsacion invertida");
            }
            System.out.println("Error al crear la llamada. Causa: " + e.getMessage());

        }finally{
            em.close();
            System.out.println("EntityManager cerrado.");
        }
        return codigoGenerado;

    }

    //Método para consultar un registro ya existente
    public static void consultarRegistroExistente() {
        EntityManager em = emf.createEntityManager();

        try{
            int idBuscar = 1000054;
            //Método 1: find() - Carga INMEDIATA (EAGER)
            System.out.println("1.Usando find(): ");
            LlamadasEmitida llamada1= em.find(LlamadasEmitida.class, idBuscar);

            if(llamada1 != null){
                System.out.println("El numero llamado es: " + llamada1.getNumeroLlamado());
                System.out.println("La duracion de la llamada es: " + llamada1.getDuracionLlamada());
                System.out.println("El importe de la llamada es: " + llamada1.getImporteLlamada());
                System.out.println("La tarjeta SIM llamante es: " + llamada1.getSimLlamante());
                System.out.println("El ID de la llamada es: " + llamada1.getId());
            }else{
                System.out.println("El numero llamado no existe");
            }

            //SE puede probar el getReference

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //Método para modificar un registro que ya exista
    public static void modificarRegistroExistente() {
         System.out.println("PASO4: Modificar registro existente");

         EntityManager em = emf.createEntityManager();
         EntityTransaction tx = null;
         try{
             tx = em.getTransaction();
             tx.begin(); //Inivio de la transaccion
             System.out.println("Transaccion iniciada (begin)");
                int idModificar = 1000054;
             /*
             Aqui empezamos con lo que hay que hacer
              */

             //1 Encontrar registro en tabla y traertelo a java "Recuperar objeto" (cargar oibjeto)
             LlamadasEmitida llamada1= em.find(LlamadasEmitida.class, idModificar);

             //2 Modificar objeto
             if(llamada1 != null) {
                 float importeAnterior = llamada1.getImporteLlamada();
                 System.out.println("IMporte anterior: " + importeAnterior + "€");

                 //2Modificamos el objeto en memoria
                 float nuevoImporte = importeAnterior * 1.1f;
                 llamada1.setImporteLlamada(nuevoImporte);
                 System.out.println("Importe nuevo: " + nuevoImporte + "€");


                 //3 Hacer el commit
                 tx.commit();
                 System.out.println("Registro actualizado con exito");
             }else{
                 System.out.println("La llamada no existe");
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

    //Método para eliminar un registro
    public static void eliminarRegistro(int codigoLlamada) {

        System.out.println("PASO5: Eliminar un registro ");

        System.out.println("Eliminando llamada: " + codigoLlamada);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;

        try{
            tx = em.getTransaction();
            tx.begin(); //Inivio de la transaccion
            System.out.println("Transaccion iniciada (begin)");


            //1 Encontrar registro en tabla y traertelo a java "Recuperar objeto" (cargar oibjeto)
            System.out.println("Recuperando el objeto con find()");
            LlamadasEmitida llamada1 = em.find(LlamadasEmitida.class, codigoLlamada);

            if(llamada1 != null) {
                System.out.println("Registro recuperado en estado persistente");

                //Eliminar con remove()
               System.out.println("Ejecutando remove");
              em.remove(llamada1);

                //3 Hacer el commit
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

   private static void gestionEstados(){
        System.out.println("PASO6: Gestion de estados");

        //A) DETACH disociar una entidad especifica
       System.out.println("A DISOCIAR CON DETACH");
       apartado_detach();

       //B) CLEAR - DISOCIAR TODAS LAS ENTIDADES
       apartado_clear();

       //C) MERGE - REASOCIAR UNA ENTIDAD DISOCIADA
       apartado_Merge();


   }

   private static void apartado_detach(){

       EntityManager em = emf.createEntityManager();
       EntityTransaction tx = null;

       try{
           tx = em.getTransaction();
           tx.begin(); //Inivio de la transaccion
           System.out.println("Transaccion iniciada (begin)");
           int codigoLlamada = 1000070;

           //1 Encontrar registro en tabla y traertelo a java "Recuperar objeto" (cargar oibjeto)
           System.out.println("Recuperando el objeto con find()");
           LlamadasEmitida llamada1 = em.find(LlamadasEmitida.class, codigoLlamada);

           if(llamada1 != null) {
               System.out.println("Registro recuperado en estado persistente");

               //De persistente a disociado
               System.out.println("Ejecutando detach");
               em.detach(llamada1);

               //3 Hacer el commit
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

   private static void apartado_clear(){
       EntityManager em = emf.createEntityManager();
       EntityTransaction tx = null;

       try{
           tx = em.getTransaction();
           tx.begin();
           System.out.println("Transacción iniciada (clear)");

           int codigoLlamada = 1000070;

           // 1) Recuperar entidad y modificarla
           System.out.println("Recuperando el objeto con find()");
           LlamadasEmitida llamada = em.find(LlamadasEmitida.class, codigoLlamada);

           if (llamada != null) {
               System.out.println("Registro en estado PERSISTENTE. Duración actual: " + llamada.getDuracionLlamada());

               // Modificamos el objeto en memoria
               llamada.setDuracionLlamada(llamada.getDuracionLlamada() + 1);
               System.out.println("Duración modificada en memoria a: " + llamada.getDuracionLlamada());

               // Limpiamos el contexto de persistencia: todas las entidades pasan a DETACHED
               System.out.println("Ejecutando clear()");
               em.clear();

               // Al hacer commit tras el clear, los cambios no se sincronizarán con la BD
               tx.commit();
               System.out.println("Commit ejecutado tras clear()");
           } else {
               System.out.println("El registro no existe");
               tx.rollback();
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

       // Verificamos en una nueva EntityManager que el cambio NO se persistió
       EntityManager em2 = emf.createEntityManager();
       try{
           LlamadasEmitida ll = em2.find(LlamadasEmitida.class, 1000070);
           if(ll != null){
               System.out.println("Verificación (nuevo EM) - Duración real en BD: " + ll.getDuracionLlamada());
           } else {
               System.out.println("Verificación (nuevo EM) - registro no encontrado");
           }
       } finally {
           em2.close();
       }
   }



   private static void apartado_Merge(){
       EntityManager em = emf.createEntityManager();
       EntityTransaction tx = null;

       try{
           tx = em.getTransaction();
           tx.begin();
           System.out.println("Transacción iniciada (merge)");

           int codigoLlamada = 1000070;

           // 1) Recuperar entidad y pasarla a estado PERSISTENTE
           System.out.println("Recuperando el objeto con find()");
           LlamadasEmitida llamada = em.find(LlamadasEmitida.class, codigoLlamada);

           if (llamada != null) {
               System.out.println("Registro recuperado en estado PERSISTENTE. Importe actual: " + llamada.getImporteLlamada());

               // 2) Desasociar la entidad (DETACH)
               System.out.println("Ejecutando detach()");
               em.detach(llamada);

               // 3) Modificar la entidad en estado DETACHED
               llamada.setImporteLlamada(llamada.getImporteLlamada() + 5.0f);
               System.out.println("Entidad DETACHED modificada. Importe nuevo (en objeto detached): " + llamada.getImporteLlamada());

               // 4) Reasociar con merge() — devuelve la instancia MANAGED
               System.out.println("Ejecutando merge()");
               LlamadasEmitida llamadaManaged = em.merge(llamada);

               // (opcional) podemos usar llamadaManaged si queremos asegurarnos de que trabajamos con la instancia gestionada
               System.out.println("Importe en instancia MANAGED después de merge(): " + llamadaManaged.getImporteLlamada());

               // 5) Commit para sincronizar los cambios con la BD
               tx.commit();
               System.out.println("Commit ejecutado. Cambios persistidos.");
           } else {
               System.out.println("El registro no existe");
               tx.rollback(); //este rollback sobraria?
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

       // Verificamos en un nuevo EM que el cambio SÍ se persistió
       EntityManager em2 = emf.createEntityManager();
       try{
           LlamadasEmitida ll = em2.find(LlamadasEmitida.class, 1000070);
           if(ll != null){
               System.out.println("Verificación (nuevo EM) - Importe real en BD: " + ll.getImporteLlamada());
           } else {
               System.out.println("Verificación (nuevo EM) - registro no encontrado");
           }
       } finally {
           em2.close();
       }
   }
}



