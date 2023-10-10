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

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Autenticacion getAutenticacion() {
        return autenticacion;
    }

    public void setAutenticacion(Autenticacion autenticacion) {
        this.autenticacion = autenticacion;
    }

    public String getnDocumento() {
        return nDocumento;
    }

    public void setnDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }

    public Tdocumento gettDocumento() {
        return tDocumento;
    }

    public void settDocumento(Tdocumento tDocumento) {
        this.tDocumento = tDocumento;
    }

    public String getpNombre() {
        return pNombre;
    }

    public void setpNombre(String pNombre) {
        this.pNombre = pNombre;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getpApellido() {
        return pApellido;
    }

    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    public String getsApellido() {
        return sApellido;
    }

    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    public int getdNacimiento() {
        return dNacimiento;
    }

    public void setdNacimiento(int dNacimiento) {
        this.dNacimiento = dNacimiento;
    }

    public int getmNacimiento() {
        return mNacimiento;
    }

    public void setmNacimiento(int mNacimiento) {
        this.mNacimiento = mNacimiento;
    }

    public int getaNacimiento() {
        return aNacimiento;
    }

    public void setaNacimiento(int aNacimiento) {
        this.aNacimiento = aNacimiento;
    }
}