package MoodleDrive.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
@Table(name = "autenticacion")
public class Autenticacion {

    private static final Logger logger = LogManager.getLogger(Autenticacion.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auth")
    private int idAuth;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "pass", nullable = false)
    private String pass;

    @Column(name = "estado", nullable = false, columnDefinition = "TINYINT default 1")
    private int estado;

    @Column(name = "token_recuperacion")
    private String tokenRecuperacion;

    @Column(name = "fecha_expiracion_token")
    private LocalDateTime fechaExpiracionToken;

    @Column(name = "fecha_registro", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_ultima_conexion")
    private LocalDateTime fechaUltimaConexion;

    @OneToMany(mappedBy = "autenticacion", fetch = FetchType.EAGER)
    private List<AsignaRol> asignaRoles;

    /**
     * Constructor predeterminado que inicializa un objeto Autenticacion sin valores específicos.
     */
    public Autenticacion() {
        this.estado = 1;
    }

    /**
     * Constructor que inicializa un objeto Autenticacion con valores específicos.
     *
     * @param email                Dirección de correo electrónico del usuario.
     * @param pass                 Contraseña del usuario.
     * @param estado               Estado de la autenticación del usuario.
     * @param tokenRecuperacion    Token para recuperación de contraseña.
     * @param fechaExpiracionToken Fecha de expiración del token.
     * @param fechaRegistro        Fecha de registro del usuario.
     */
    public Autenticacion(String email, String pass, int estado, String tokenRecuperacion, LocalDateTime fechaExpiracionToken, LocalDateTime fechaRegistro) {
        this.email = email;
        this.pass = pass;
        this.estado = estado;
        this.tokenRecuperacion = tokenRecuperacion;
        this.fechaExpiracionToken = fechaExpiracionToken;
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return El ID único de autenticación.
     */
    public int getIdAuth() {
        return idAuth;
    }

    /**
     * @param idAuth ID de autenticación a establecer.
     */
    public void setIdAuth(int idAuth) {
        this.idAuth = idAuth;
    }

    /**
     * @return Dirección de correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email Dirección de correo electrónico a establecer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return La contraseña del usuario.
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass Contraseña a establecer.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return El estado de autenticación del usuario.
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado Estado de autenticación a establecer.
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return El token de recuperación de contraseña.
     */
    public String getTokenRecuperacion() {
        return tokenRecuperacion;
    }

    /**
     * @param tokenRecuperacion Token de recuperación a establecer.
     */
    public void setTokenRecuperacion(String tokenRecuperacion) {
        this.tokenRecuperacion = tokenRecuperacion;
    }

    /**
     * @return La fecha de expiración del token de recuperación.
     */
    public LocalDateTime getFechaExpiracionToken() {
        return fechaExpiracionToken;
    }

    /**
     * @param fechaExpiracionToken Fecha de expiración del token a establecer.
     */
    public void setFechaExpiracionToken(LocalDateTime fechaExpiracionToken) {
        this.fechaExpiracionToken = fechaExpiracionToken;
    }

    /**
     * @return La fecha de registro del usuario.
     */
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro Fecha de registro a establecer.
     */
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return La fecha de ultima conexion.
     */
    public LocalDateTime getFechaUltimaConexion() {
        return fechaUltimaConexion;
    }

    /**
     * @param fechaUltimaConexion La fecha de ultima conexion.
     */
    public void setFechaUltimaConexion(LocalDateTime fechaUltimaConexion) {
        this.fechaUltimaConexion = fechaUltimaConexion;
    }

    public List<AsignaRol> getAsignaRoles() {
        return asignaRoles;
    }

    public void setAsignaRoles(List<AsignaRol> asignaRoles) {
        this.asignaRoles = asignaRoles;
    }
}
