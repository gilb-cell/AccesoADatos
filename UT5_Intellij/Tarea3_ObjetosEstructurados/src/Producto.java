
import jakarta.persistence.*;
/**
 * Gilberto
 * Tarea3 Ut5
 */

@Entity
public class Producto {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto() {} //Constructor sin parametros

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return nombre + " - " + precio + "€ (Stock: " + stock + ")";
    }
}
