package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class AppMain {

    //EntityManagerFactory: fabruca q crea EntityManagers (una por aplicacion)AppMain
    static String persistenceUnitName = "default"; //nombre de la persistence unit en el archivo persistence
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);

    public static void main(String[] args) {
        try {

//Consulta simple SQL
            System.out.println("---APARTADO 1: Consulta simple con SQL nativo--- ");
            consultaSimpleSQL();

//Consulta filtrada
            System.out.println("\n---APARTADO 2: Consulta filtrada con SQL nativo- --");
            consultaFiltradaSQL();

//Consulta simple JPQL
            System.out.println("\n---APARTADO 3: Consulta simple con JPQL---");
            consultaSimpleJPQL();

//Consulta filtrada JPQL
            System.out.println("\n---APARTADO 4: Consulta filtrada con JPQL---");
            consultaFiltradaJPQL();

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
            System.out.println("\nRecursos liberados correctamente.");
        }
    }
    //Método apartado 1: Consulta simple SQL
    private static void consultaSimpleSQL() {
//EntityManager: Gestor que maneja las operaciones con la BD (UNO poroperacion)
        EntityManager em = emf.createEntityManager();
        System.out.println("\nListado de TODAS las llamadas emitidas:\n");
        try {
//Paso 1: Creamos la consulta SQL nativa
            String sql = "SELECT * FROM LLAMADAS_EMITIDAS";
            Query query = em.createNativeQuery(sql);
//Paso 2: Ejecutar la consulta y obtener resultados
//getResultList() devuelve una lista de Object[] porque noe especificamos que clase Java mapea los resultados
            List<Object[]> resultados = query.getResultList();
//Paso 3: Verificar si hay resultados
            if (resultados.isEmpty()) {
                System.out.println("No se encontraron llamadas en la base de datos.");
                return;
            }
//Paso 4: Mostrar los resultados de forma organizada
//Encabezado tabla
            String encabezado1 = "Sim Llamante";
            String encabezado2 = "Numero Llamado";
            String encabezado3 = "Importe";
            String linea = "-".repeat(55);
            System.out.print(linea + "\n");
            System.out.printf("| %-15s | %-15s | %-15s |%n", encabezado1,
                    encabezado2, encabezado3);
            System.out.println(linea);
//Paso 5: Iterar sobre cada fila de resultados
            for (Object[] fila : resultados) {


//int codigoLlamada = (Integer) fila[0];
                int simLlamante = (Integer) fila[1];
                int numeroLlamado = (Integer) fila[2];
//int duracion = (Integer) fila[3];
                float importe = (Float) fila[4];
//Formatear la salida para que sea legible
                System.out.printf("| %-15d | %-15d | %-15.2f |%n", simLlamante,

                        numeroLlamado, importe);
            }
            System.out.println(linea);
            System.out.println("\nTotal de llamadas encontradas: " +
                    resultados.size());

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    private static void consultaFiltradaSQL() {
        EntityManager em = emf.createEntityManager();
        int duracionMinima = 300;
        System.out.println("\nListado de llamadas con duración superior a " +
                duracionMinima + " segundos:\n");
        try {
//Paso 1: Crear la consulta SQL nativa
            String sql = "SELECT * FROM LLAMADAS_EMITIDAS WHERE DURACION_LLAMADA > ?";
            Query query = em.createNativeQuery(sql);
//Paso 2: Asignar valor al parámetro
//Los parámetros se numeran desde 1 (primer ?, segundo ?, etc.)
            query.setParameter(1, duracionMinima);
//Paso 3: Ejecutar la consulta y obtener resultados
            List<Object[]> resultados = query.getResultList();
//Paso 4: verificar si hay resultados:


            if (resultados.isEmpty()) {
                System.out.println("No se encontraron llamadas con duración superior a " +
                        "" + duracionMinima + " segundos.");
                return;
            }
//Encabezado tabla
            String encabezado1 = "ID Llamada";
            String encabezado2 = "Sim Llamante";
            String encabezado3 = "Numero Llamado";
            String encabezado4 = "Duración";
            String encabezado5 = "Importe";
            String linea = "-".repeat(91);
            System.out.print(linea + "\n");
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s |%n",
                    encabezado1, encabezado2, encabezado3, encabezado4, encabezado5);
            System.out.println(linea);
//Paso 5: mostrar todos los resultados de forma organizada:
//Paso 6: Iterar sobre cada fila de resultados
            for (Object[] fila : resultados) {
                int codigoLlamada = (Integer) fila[0];
                int simLlamante = (Integer) fila[1];
                int numeroLlamado = (Integer) fila[2];
                int duracion = (Integer) fila[3];
                float importe = (Float) fila[4];
//Formatear la salida para que sea legible
                System.out.printf("| %-15d | %-15d | %-15d | %-15d | %-15.2f |%n",
                        codigoLlamada, simLlamante, numeroLlamado, duracion, importe);
            }
            System.out.println(linea);
            System.out.println("\nTotal de llamadas encontradas: " + resultados.size());
        }catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    private static void consultaSimpleJPQL() {
        EntityManager em = emf.createEntityManager();
        System.out.println("\nListado de TODAS las llamadas emitidas usando JPQL:\n");



        try {
//Paso 1: Crear la consulta JPQL (nombre de la clase java generada, node la tabla de la BBDD)
//l: alias usamos para referirnos a cada objeto LlamadasEmitida
            String jpql = "SELECT l FROM LlamadasEmitida l";
            Query query = em.createQuery(jpql);
//Paso 2: Ejecutar la consulta y obtener resultados:
            List<LlamadasEmitida> resultados = query.getResultList();
//Paso 3: verificar si hay resultados
            if (resultados.isEmpty()) {
                System.out.println("No se encontraron llamadas en la base de datos.");
                return;
            }
// Encabezados
            String encabezado1 = "Código";
            String encabezado2 = "Número";
            String encabezado3 = "Duración";
            String encabezado4 = "Importe";
            String linea = "-".repeat(73);
            System.out.println(linea);
            System.out.printf("| %-15s | %-15s | %-15s | %-15s |%n",
                    encabezado1, encabezado2, encabezado3, encabezado4);
            System.out.println(linea);
//Paso 5:iterar sobre cada objeto llamadasemitidas
            for (LlamadasEmitida llamada : resultados) {
//No necesitamos hacer casting ni acceder por indice como en SQL nativo

                int codigoLlamada = llamada.getId();
//int simLlamante = llamada.getSimLlamante();
                int numeroLlamado = llamada.getNumeroLlamado();
                int duracion = llamada.getDuracionLlamada();
                float importe = llamada.getImporteLlamada();
//Formatear la salida para que sea legible
                System.out.printf("| %-15d | %-15d | %-15d | %-15.2f |%n",

                        codigoLlamada, numeroLlamado, duracion, importe);
            }
            System.out.println(linea);
            System.out.println("\nTotal de llamadas encontradas: " +
                    resultados.size());
        } catch (Exception e) {


            System.err.println("ERROR: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    private static void consultaFiltradaJPQL() {
        EntityManager em = emf.createEntityManager();
        int duracionMinima = 300;
        System.out.println("\nListado de las llamadas con duración superior a " +
                duracionMinima + " usando JPQL:\n");
        try {
//Paso 1: Crear la consulta JPQL (nombre de la clase java generada, node la tabla de la BBDD)
//l: alias usamos para referirnos a cada objeto LlamadasEmitida
            String jpql = "SELECT l FROM LlamadasEmitida l WHERE l.duracionLlamada > :duracionMinima";
            Query query = em.createQuery(jpql);
            query.setParameter("duracionMinima", duracionMinima);

//Paso 2: Ejecutar la consulta y obtener resultados:
            List<LlamadasEmitida> resultados = query.getResultList();
//Paso 3: verificar si hay resultados
            if (resultados.isEmpty()) {
                System.out.println("No se encontraron llamadas en la base de datos.");
                return;
            }
// Encabezados
            String encabezado1 = "Código";
            String encabezado2 = "Número";
            String encabezado3 = "Duración";
            String encabezado4 = "Importe";
            String linea = "-".repeat(73);
            System.out.println(linea);
            System.out.printf("| %-15s | %-15s | %-15s | %-15s |%n",
                    encabezado1, encabezado2, encabezado3, encabezado4);
            System.out.println(linea);
//Paso 4:iterar sobre cada objeto llamadasemitidas



            for (LlamadasEmitida llamada : resultados) {
//No necesitamos hacer casting ni acceder por indice como en SQL nativo

                int codigoLlamada = llamada.getId();
//int simLlamante = llamada.getSimLlamante();
                int numeroLlamado = llamada.getNumeroLlamado();
                int duracion = llamada.getDuracionLlamada();
                float importe = llamada.getImporteLlamada();
//Formatear la salida para que sea legible
                System.out.printf("| %-15d | %-15d | %-15d | %-15.2f |%n",

                        codigoLlamada, numeroLlamado, duracion, importe);
            }
            System.out.println(linea);
            System.out.println("\nTotal de llamadas encontradas: " +
                    resultados.size());
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}


