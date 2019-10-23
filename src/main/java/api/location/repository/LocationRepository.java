package api.location.repository;

import api.location.repository.domain.LocationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<LocationEntity, String> {
}
