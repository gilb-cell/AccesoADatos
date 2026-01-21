import jakarta.persistence.*;

import java.util.List;

@Entity
public class Mision {

    @Id @GeneratedValue
    private Long ID;
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Personaje> personajes;

    public Mision() {} //Constructor sin parametros

    public Mision(String descripcion,  List<Personaje> personajes) {
        this.descripcion = descripcion;
        this.personajes = personajes;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setProductos(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    @Override
    public String toString() {
        return "Mision{" +
                "ID=" + ID +
                ", descripcion='" + descripcion + '\'' +
                ", productos=" + personajes +
                '}';
    }
}
