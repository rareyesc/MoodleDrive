package MoodleDrive.Repositories;

import MoodleDrive.Models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    boolean existsBynDocumento(String numeroDocumento);
    @Query("SELECT p FROM Perfil p WHERE p.autenticacion.idAuth = :authId")
    Optional<Perfil> findByAuthId(@Param("authId") int authId);
}
