package MoodleDrive.Repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import MoodleDrive.Models.Autenticacion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AutenticacionRepository extends JpaRepository<Autenticacion, Integer> {
    boolean existsByEmail(String email);
    Autenticacion findByEmail(String email);
    @Transactional
    @Modifying
    @Query("update Autenticacion a set a.fechaUltimaConexion = :fechaUltimaConexion where a.email = :email")
    void updateUltimaConexion(@Param("email") String email, @Param("fechaUltimaConexion") LocalDateTime fechaUltimaConexion);

    Optional<Autenticacion> findByTokenRecuperacion(String tokenRecuperacion);
}
