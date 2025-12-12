import jakarta.persistence.*;

import java.util.List;

/**
 * Gilberto
 * Tarea4 UT5
 */

public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "C:/Users/AlumnoDAM2/Desktop/Acceso_Datos/ObjectDB/objectdb-2.9.4/objectdb-2.9.4/db/InventarioCompleto.odb");

    public static void main(String[] args) {

      //  consultarProductosPorStock();
        //  modificarRegistroExistente();
          modificarStockExistente();

    }


    //Método para consultar un registro ya existente
    public static void consultarProductosPorStock() {

        EntityManager em = emf.createEntityManager();
        System.out.println("\nListado de TODOS los productos en stock:\n");
        try {

            String consulta = "SELECT p FROM Producto p WHERE p.stock < 20 ";
            TypedQuery<Producto> q = em.createQuery(consulta, Producto.class);
            List<Producto> productos = q.getResultList();

            if (productos.isEmpty()) {
                System.out.println("No encontrados productos con stock bajo");
            }else{
                System.out.println("Productos con menos de 20 unidades en stock.");

                for (Producto producto : productos) {
                    System.out.println(producto);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }


    //Método para modificar un registro que ya exista
    public static void modificarRegistroExistente() {
        System.out.println(" Modificar registro existente");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        //o	Busca un producto por su ID (id = 5) y modifica su precio (140 €).

        try{
            tx = em.getTransaction();
            tx.begin();
            System.out.println("Transsacion Iniciada");

            int idProductoModificar = 5;


            Producto p = em.find(Producto.class, idProductoModificar);

            //Modificamos el producto
            if(p != null){
                double precioAnterior = p.getPrecio();
                System.out.println("El precio anterior del producto es: " +precioAnterior + "€");

                //Modificamos el objeto con el precio nuevo
                double precioNuevo = 140;
                p.setPrecio(precioNuevo);
                System.out.println("El precio nuevo es: " + precioNuevo + "€");

                //Hacemos commit para confirmar transaccion
                tx.commit();
                System.out.println("Precio modificado con exito");
            }else{
                System.out.println("No existe el producto");
            }


        }catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
                System.out.println("Ejecutandose rollback");
            }
            System.out.println("causa:" + e.getMessage());
        }finally{
            em.close();
            System.out.println("Se cerro el Entity Manager");
        }


    }


    //Metodo Actualizar stock de un producto
    public static void modificarStockExistente() {
        System.out.println(" Modificar Stock existente");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;

        //o	Busca un producto por su ID (id=17) y ajusta su cantidad de stock (tres unidades menos).

        try{
            tx = em.getTransaction();
            tx.begin();
            System.out.println("Transsacion Iniciada");

            int idProductoStockModificar = 17;


            Producto p = em.find(Producto.class, idProductoStockModificar);

            //Modificamos el producto
            if(p != null){
                int stockAnterior = p.getStock();
                System.out.println("El stock anterior del producto es: " + stockAnterior + "unidades");

                //Modificamos el objeto con el stock nuevo
                int stockNuevo = 7;
                p.setStock(stockNuevo);
                System.out.println("El stock nuevo es: " + stockNuevo + "unidades");

                //Hacemos commit para confirmar transaccion
                tx.commit();
                System.out.println("Stock modificado con exito");
            }else{
                System.out.println("No existe el producto");
            }


        }catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
                System.out.println("Ejecutandose rollback");
            }
            System.out.println("causa:" + e.getMessage());
        }finally{
            em.close();
            System.out.println("Se cerro el Entity Manager");
        }


    }
}
