

import jakarta.persistence.*;
import java.util.List;

/**
 * Gilberto
 * Tarea3 Ut5
 */
@Entity
public class Pedido {

    @Id
    private String codigo;
    private Long idCliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Producto> productos;

    public Pedido() {} //Constructor sin parametros

    public Pedido(String codigo, Long idCliente, List<Producto> productos) {
        this.codigo = codigo;
        this.idCliente = idCliente;
        this.productos = productos;
    }

    public String getCodigo() { return codigo; }
    public Long getIdCliente() { return idCliente; }
    public List<Producto> getProductos() { return productos; }
}

