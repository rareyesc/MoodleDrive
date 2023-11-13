package MoodleDrive.Repositories;

import MoodleDrive.DTO.UsuarioDTO;
import MoodleDrive.Models.Autenticacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Autenticacion, Integer> {

    @Query("SELECT new MoodleDrive.DTO.UsuarioDTO(" +
            "p.autenticacion.idAuth, " +
            "p.pNombre, " +
            "p.sNombre, " +
            "p.pApellido, " +
            "p.sApellido, " +
            "p.autenticacion.email, " +
            "td.nombre, " +
            "p.nDocumento, " +
            "p.aNacimiento, " +
            "r.nombreRol, " +
            "p.autenticacion.estado) " +
            "FROM Perfil p " +
            "JOIN p.tDocumento td " +
            "JOIN p.autenticacion.asignaRoles ar " +
            "JOIN ar.rol r")
    List<UsuarioDTO> findAllUsuarios();

}