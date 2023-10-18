package MoodleDrive.Models;

import jakarta.persistence.*;
/**
 * Clase que representa un tipo de documento en el sistema.
 */
@Entity
@Table(name = "t_documento")
public class Tdocumento {
    /**
     * Identificador único para el tipo de documento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idt_documento")
    private int idtDocumento;
    /**
     * Nombre del tipo de documento (e.g., Cédula, Pasaporte).
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;
    /**
     * Constructor por defecto.
     * Crea una nueva instancia de Tdocumento sin inicializar las propiedades.
     */
    public Tdocumento() {
    }
    /**
     * Constructor que inicializa la propiedad nombre del tipo de documento.
     *
     * @param nombre El nombre del tipo de documento.
     */
    public Tdocumento(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return El identificador único del tipo de documento.
     */
    public int getIdtDocumento() {
        return idtDocumento;
    }
    /**
     * @param idtDocumento El nuevo identificador único del tipo de documento.
     */
    public void setIdtDocumento(int idtDocumento) {
        this.idtDocumento = idtDocumento;
    }
    /**
     * @return El nombre del tipo de documento.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @param nombre El nuevo nombre del tipo de documento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}