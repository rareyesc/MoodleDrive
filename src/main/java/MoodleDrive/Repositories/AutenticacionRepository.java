package MoodleDrive.Repositories;

import MoodleDrive.Models.Autenticacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutenticacionRepository extends JpaRepository<Autenticacion, Integer> {
}
