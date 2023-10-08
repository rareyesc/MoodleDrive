package MoodleDrive.Repositories;

import MoodleDrive.Models.AsignaRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaRolRepository extends JpaRepository<AsignaRol, Integer> {
}
