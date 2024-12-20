package fact.it.artiestservice.repository;

import fact.it.artiestservice.model.Artiest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtiestRepository extends MongoRepository<Artiest, String> {
    // JPA Repository provides CRUD and custom query capabilities
}


