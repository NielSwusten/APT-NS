package fact.it.artiestservice.repository;

import fact.it.artiestservice.model.Artiest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArtiestRepository extends MongoRepository<Artiest, String> {
    List<Artiest> findBySkuCodeIn(List<String> skuCode);
}
