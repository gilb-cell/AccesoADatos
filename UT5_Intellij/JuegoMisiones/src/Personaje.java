import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Personaje {

    //Atributos
    @Id
    @GeneratedValue
    private Long ID;

    private String nombre;
    private String clasePersonaje;
    private int nivel;

    //Constructor sin parametros
    public Personaje() {}

    public Personaje( String nombre, String clasePersonaje, int nivel) {
        this.nombre = nombre;
        this.clasePersonaje = clasePersonaje;
        this.nivel = nivel;
    }




    //Getters y Setters
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClasePersonaje() {
        return clasePersonaje;
    }

    public void setClasePersonaje(String clasePersonaje) {
        this.clasePersonaje = clasePersonaje;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", clasePersonaje='" + clasePersonaje + '\'' +
                ", nivel=" + nivel +
                '}';
    }
}

