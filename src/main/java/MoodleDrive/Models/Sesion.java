package MoodleDrive.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
@Table(name = "sesion")
public class Sesion {

    private static final Logger logger = LogManager.getLogger(Sesion.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private int idSesion;

    @ManyToOne
    @JoinColumn(name = "id_auth", nullable = false)
    private Autenticacion autenticacion;

    @Column(name = "fecha_creacion", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "estado", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private int estado;

    /**
     * Constructor predeterminado que inicializa un objeto Sesion sin valores específicos.
     */
    public Sesion() {
        this.estado = 1;
    }

    /**
     * Constructor que inicializa un objeto Sesion con valores específicos.
     *
     * @param autenticacion    Objeto Autenticacion asociado.
     * @param fechaCreacion    Fecha de creación de la sesión.
     * @param fechaActualizacion Fecha de última actualización de la sesión.
     */
    public Sesion(Autenticacion autenticacion, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.autenticacion = autenticacion;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public Autenticacion getAutenticacion() {
        return autenticacion;
    }

    public void setAutenticacion(Autenticacion autenticacion) {
        this.autenticacion = autenticacion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}