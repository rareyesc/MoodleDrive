package MoodleDrive.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "asigna_rol")
public class AsignaRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asigna_rol")
    private int idAsignaRol;

    @ManyToOne
    @JoinColumn(name = "id_auth")
    private Autenticacion autenticacion;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    /**
     * Constructor predeterminado que inicializa un objeto AsignaRol sin valores
     * específicos.
     */
    public AsignaRol() {
    }

    public AsignaRol(Autenticacion autenticacion, Rol rol) {
        this.autenticacion = autenticacion;
        this.rol = rol;
    }

    public int getIdAsignaRol() {
        return idAsignaRol;
    }

    public void setIdAsignaRol(int idAsignaRol) {
        this.idAsignaRol = idAsignaRol;
    }

    public Autenticacion getAutenticacion() {
        return autenticacion;
    }

    public void setAutenticacion(Autenticacion autenticacion) {
        this.autenticacion = autenticacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
