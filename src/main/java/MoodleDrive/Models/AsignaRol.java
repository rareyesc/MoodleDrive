package MoodleDrive.Models;

import jakarta.persistence.*;
/**
 * Representa una asignación de rol en la aplicación.
 * Esta clase mapea la relación entre una autenticación y un rol en la base de datos.
 */
@Entity
@Table(name = "asigna_rol")
public class AsignaRol {
    /**
     * Identificador único de la asignación de rol.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asigna_rol")
    private int idAsignaRol;
    /**
     * Referencia a la autenticación asociada a esta asignación de rol.
     */
    @ManyToOne
    @JoinColumn(name = "id_auth")
    private Autenticacion autenticacion;
    /**
     * Referencia al rol asociado a esta asignación de rol.
     */
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
    /**
     * Constructor predeterminado que inicializa un objeto AsignaRol sin valores específicos.
     */
    public AsignaRol() {
    }
    /**
     * Constructor que inicializa un objeto AsignaRol con una autenticación y un rol específicos.
     *
     * @param autenticacion La autenticación asociada a esta asignación de rol.
     * @param rol El rol asociado a esta asignación de rol.
     */
    public AsignaRol(Autenticacion autenticacion, Rol rol) {
        this.autenticacion = autenticacion;
        this.rol = rol;
    }
    /**
     * Retorna el identificador único de esta asignación de rol.
     *
     * @return El identificador único de esta asignación de rol.
     */
    public int getIdAsignaRol() {
        return idAsignaRol;
    }
    /**
     * Establece el identificador único de esta asignación de rol.
     *
     * @param idAsignaRol El nuevo identificador único de esta asignación de rol.
     */
    public void setIdAsignaRol(int idAsignaRol) {
        this.idAsignaRol = idAsignaRol;
    }
    /**
     * Retorna la autenticación asociada a esta asignación de rol.
     *
     * @return La autenticación asociada a esta asignación de rol.
     */
    public Autenticacion getAutenticacion() {
        return autenticacion;
    }
    /**
     * Establece la autenticación asociada a esta asignación de rol.
     *
     * @param autenticacion La nueva autenticación asociada a esta asignación de rol.
     */
    public void setAutenticacion(Autenticacion autenticacion) {
        this.autenticacion = autenticacion;
    }
    /**
     * Retorna el rol asociado a esta asignación de rol.
     *
     * @return El rol asociado a esta asignación de rol.
     */
    public Rol getRol() {
        return rol;
    }
    /**
     * Establece el rol asociado a esta asignación de rol.
     *
     * @param rol El nuevo rol asociado a esta asignación de rol.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
