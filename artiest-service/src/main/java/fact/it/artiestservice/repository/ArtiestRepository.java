package fact.it.artiestservice.repository;

import fact.it.artiestservice.model.Artiest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtiestRepository extends JpaRepository<Artiest, Long> {
    // JPA Repository provides CRUD and custom query capabilities
}