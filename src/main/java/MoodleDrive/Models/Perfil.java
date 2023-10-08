package MoodleDrive.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerfil")
    private int idPerfil;

    @ManyToOne
    @JoinColumn(name = "idAuth", nullable = false)
    private Autenticacion autenticacion;

    @NotBlank
    @Column(name = "nDocumento", nullable = false, unique = true)
    private String nDocumento;

    @ManyToOne
    @JoinColumn(name = "t_documento", nullable = false)
    private Tdocumento tDocumento;

    @NotBlank
    @Column(name = "pNombre", nullable = false)
    private String pNombre;

    @Column(name = "sNombre")
    private String sNombre;

    @NotBlank
    @Column(name = "pApellido", nullable = false)
    private String pApellido;

    @Column(name = "sApellido")
    private String sApellido;

    @Min(1)
    @Max(31)
    @Column(name = "dNacimiento", nullable = false)
    private int dNacimiento;

    @Min(1)
    @Max(12)
    @Column(name = "mNacimiento", nullable = false)
    private int mNacimiento;

    @Min(1950)
    @Column(name = "aNacimiento", nullable = false)
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