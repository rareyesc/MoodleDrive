package MoodleDrive.Models;

import jakarta.persistence.*;

/**
 * Representa los roles de usuario en el sistema. Esta clase modela
 * los diferentes roles que un usuario puede tener, como administrador, cliente, entre otros.
 *
 * @author Ruben Andres Reyes Cuellar
 */

@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol")
    private int idRol;

    @Column(name = "nombreRol", unique = true, nullable = false)
    private String nombreRol;

    /**
     * Constructor predeterminado que inicializa un objeto Rol sin valores específicos.
     */
    public Rol() {
    }

    /**
     * Constructor que inicializa un objeto Rol con un nombre específico.
     *
     * @param nombreRol Nombre descriptivo del rol.
     */
    public Rol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    /**
     * @return El ID único del rol.
     */
    public int getIdRol() {
        return idRol;
    }

    /**
     * Establece el ID único del rol.
     *
     * @param idRol ID de rol a establecer.
     */
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    /**
     * @return El nombre descriptivo del rol.
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * Establece el nombre descriptivo del rol.
     *
     * @param nombreRol Nombre del rol a establecer.
     */
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
