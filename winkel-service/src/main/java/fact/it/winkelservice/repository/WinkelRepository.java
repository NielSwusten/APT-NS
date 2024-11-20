package fact.it.winkelservice.repository;

import fact.it.winkelservice.model.Winkel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface WinkelRepository extends JpaRepository<Winkel, Long> {
}
