
package org.example;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement( name = "usuario") //Importante, la API interpreta esto como el nodo raiz del XML

public class Usuario {
//Atributos
    /**
     * Identificador único del usuario (atributo del nodo XML).
     */
    private String id;


    /**
     * Nombre completo del usuario.
     */
    private String nombre;


    /**
     * Correo electrónico del usuario.
     */
    private String email;


    /**
     * Rol o tipo de usuario (por ejemplo: admin, editor, invitado, etc.).
     */
    private String rol;


    /**
     * Constructor vacío obligatorio para JAXB.
     * <p>Se requiere para poder crear instancias de esta clase al deserializar XML.</p>
     */

    public Usuario() {}; //Importante tener siempre un constructor vacio , esto lo quiere la API JAXB y RUNTIME

    public Usuario(String id, String nombre, String email, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }


    //Getters y setters
    /**
     * Obtiene el identificador del usuario.
     *
     * @return el ID del usuario.
     *
     * Los @ en color amarillo es para que la API interpreta y convierta la estructura a XML, si no ella no lo sabe
     */
    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }


    /**
     * Establece el identificador del usuario.
     *
     * @param id el nuevo ID del usuario.
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario.
     */
    @XmlElement
    public String getNombre() {
        return nombre;
    }


    /**
     * Establece el nombre del usuario.
     *
     * @param nombre el nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return el email del usuario.
     */
    @XmlElement
    public String getEmail() {
        return email;
    }


    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email el nuevo correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Obtiene el rol del usuario.
     *
     * @return el rol del usuario.
     */
    @XmlElement
    public String getRol() {
        return rol;
    }


    /**
     * Establece el rol del usuario.
     *
     * @param rol el nuevo rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}


