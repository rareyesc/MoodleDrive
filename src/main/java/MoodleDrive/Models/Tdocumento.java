package MoodleDrive.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "t_documento")
public class Tdocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idt_documento")
    private int idtDocumento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    public Tdocumento() {
    }

    public Tdocumento(String nombre) {
        this.nombre = nombre;
    }

    public int getIdtDocumento() {
        return idtDocumento;
    }

    public void setIdtDocumento(int idtDocumento) {
        this.idtDocumento = idtDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}