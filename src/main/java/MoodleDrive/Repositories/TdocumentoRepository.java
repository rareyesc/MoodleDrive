package MoodleDrive.Repositories;

import MoodleDrive.Models.Tdocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TdocumentoRepository extends JpaRepository<Tdocumento, Integer> {
}
