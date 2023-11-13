package MoodleDrive.Repositories;

import MoodleDrive.Models.Sesion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Integer> {

    @Query("SELECT s FROM Sesion s WHERE s.autenticacion.idAuth = :idAuth AND s.estado = 1 ORDER BY s.fechaCreacion DESC")
    Optional<Sesion> findUltimaSesionActiva(@Param("idAuth") int idAuth);

}
