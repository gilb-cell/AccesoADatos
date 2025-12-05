import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Gilberto Tarea2Ut5
 */

@Entity
public class Producto {

    //Atributos
    @Id @GeneratedValue
    private int ID;

    private String nombre;
    private double Precio;
    private boolean stock;

    public Producto(String nombre, double Precio, boolean stock) {
        this.nombre = nombre;
        this.Precio = Precio;
        this.stock = stock;
    }

    //Constructor sin constructor necesario
    public  Producto() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", Precio=" + Precio +
                ", stock=" + stock +
                '}';
    }
}
