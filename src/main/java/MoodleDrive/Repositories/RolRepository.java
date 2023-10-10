package MoodleDrive.Repositories;

import MoodleDrive.Models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    @Query("SELECT r.id FROM Rol r WHERE r.nombreRol = :nombreRol")
    Integer findIdByNombreRol(@Param("nombreRol") String nombreRol);
}
