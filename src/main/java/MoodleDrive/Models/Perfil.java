package MoodleDrive.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private int idPerfil;

    @ManyToOne
    @JoinColumn(name = "id_auth", nullable = false)
    private Autenticacion autenticacion;

    @NotNull(message = "El numero de documento no puede estar vacío")
    @NotBlank
    @Column(name = "n_documento", nullable = false, unique = true)
    private String nDocumento;

    @NotNull(message = "El tipo de documento no puede estar vacío")
    @ManyToOne
    @JoinColumn(name = "t_documento", nullable = false)
    private Tdocumento tDocumento;

    @NotNull(message = "El primer nombre no puede estar vacío")
    @NotBlank
    @Column(name = "p_nombre", nullable = false)
    private String pNombre;

    @Column(name = "s_nombre")
    private String sNombre;

    @NotNull(message = "El primer apellido no puede estar vacío")
    @NotBlank
    @Column(name = "p_apellido", nullable = false)
    private String pApellido;

    @Column(name = "s_apellido")
    private String sApellido;

    @NotNull(message = "El dia de nacimiento no puede estar vacío")
    @Min(1)
    @Max(31)
    @Column(name = "d_nacimiento", nullable = false)
    private int dNacimiento;

    @NotNull(message = "El mes de nacimiento no puede estar vacío")
    @Min(1)
    @Max(12)
    @Column(name = "m_nacimiento", nullable = false)
    private int mNacimiento;

    @NotNull(message = "El año de nacimiento no puede estar vacío")
    @Min(1950)
    @Column(name = "a_nacimiento", nullable = false)
    private int aNacimiento;

    /**
     * Constructor predeterminado que inicializa un objeto Perfil sin valores específicos.
     */
    public Perfil() {
    }
    /**
     * Constructor para inicializar un objeto Perfil con valores específicos.
     *
     * @param autenticacion Autenticación asociada a este perfil.
     * @param nDocumento    Número de documento del individuo.
     * @param tDocumento    Tipo de documento del individuo.
     * @param pNombre       Primer nombre del individuo.
     * @param sNombre       Segundo nombre del individuo.
     * @param pApellido     Primer apellido del individuo.
     * @param sApellido     Segundo apellido del individuo.
     * @param dNacimiento   Día de nacimiento del individuo.
     * @param mNacimiento   Mes de nacimiento del individuo.
     * @param aNacimiento   Año de nacimiento del individuo.
     */
    public Perfil(Autenticacion autenticacion, String nDocumento, Tdocumento tDocumento, String pNombre, String sNombre, String pApellido, String sApellido, int dNacimiento, int mNacimiento, int aNacimiento) {
        this.autenticacion = autenticacion;
        this.nDocumento = nDocumento;
        this.tDocumento = tDocumento;
        this.pNombre = pNombre;
        this.sNombre = sNombre;
        this.pApellido = pApellido;
        this.sApellido = sApellido;
        this.dNacimiento = dNacimiento;
        this.mNacimiento = mNacimiento;
        this.aNacimiento = aNacimiento;
    }
    /**
     * @return El identificador único del perfil.
     */
    public int getIdPerfil() {
        return idPerfil;
    }
    /**
     * @param idPerfil El nuevo identificador único del perfil.
     */
    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }
    /**
     * @return La autenticación asociada a este perfil.
     */
    public Autenticacion getAutenticacion() {
        return autenticacion;
    }
    /**
     * @param autenticacion La nueva autenticación asociada a este perfil.
     */
    public void setAutenticacion(Autenticacion autenticacion) {
        this.autenticacion = autenticacion;
    }
    /**
     * @return El número de documento del individuo.
     */
    public String getnDocumento() {
        return nDocumento;
    }
    /**
     * @param nDocumento El nuevo número de documento del individuo.
     */
    public void setnDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }
    /**
     * @return El tipo de documento del individuo.
     */
    public Tdocumento gettDocumento() {
        return tDocumento;
    }
    /**
     * @param tDocumento El nuevo tipo de documento del individuo.
     */
    public void settDocumento(Tdocumento tDocumento) {
        this.tDocumento = tDocumento;
    }
    /**
     * @return El primer nombre del individuo.
     */
    public String getpNombre() {
        return pNombre;
    }
    /**
     * @param pNombre El nuevo primer nombre del individuo.
     */
    public void setpNombre(String pNombre) {
        this.pNombre = pNombre;
    }
    /**
     * @return El segundo nombre del individuo.
     */
    public String getsNombre() {
        return sNombre;
    }
    /**
     * @param sNombre El nuevo segundo nombre del individuo.
     */
    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }
    /**
     * @return El primer apellido del individuo.
     */
    public String getpApellido() {
        return pApellido;
    }
    /**
     * @param pApellido El nuevo primer apellido del individuo.
     */
    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }
    /**
     * @return El segundo apellido del individuo.
     */
    public String getsApellido() {
        return sApellido;
    }
    /**
     * @param sApellido El nuevo segundo apellido del individuo.
     */
    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }
    /**
     * @return El día de nacimiento del individuo.
     */
    public int getdNacimiento() {
        return dNacimiento;
    }
    /**
     * @param dNacimiento El nuevo día de nacimiento del individuo.
     */
    public void setdNacimiento(int dNacimiento) {
        this.dNacimiento = dNacimiento;
    }
    /**
     * @return El mes de nacimiento del individuo.
     */
    public int getmNacimiento() {
        return mNacimiento;
    }
    /**
     * @param mNacimiento El nuevo mes de nacimiento del individuo.
     */
    public void setmNacimiento(int mNacimiento) {
        this.mNacimiento = mNacimiento;
    }
    /**
     * @return El año de nacimiento del individuo.
     */
    public int getaNacimiento() {
        return aNacimiento;
    }
    /**
     * @param aNacimiento El nuevo año de nacimiento del individuo.
     */
    public void setaNacimiento(int aNacimiento) {
        this.aNacimiento = aNacimiento;
    }
}