package api.location.service;

import api.location.model.LocationEntry;
import api.location.repository.LocationRepository;
import api.location.repository.domain.LocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GPSLocationService implements LocationService {

    /**
     * The Attribute that is used for the search for the start position
     */
    private static final String START = "start";

    private LocationRepository repository;

    private MongoOperations mongoOps;

    @Autowired
    public GPSLocationService(LocationRepository repository, MongoOperations mongoOps) {
        this.repository = repository;
        this.mongoOps = mongoOps;
    }

    @Override
    public List<LocationEntity> getLocationsInBox(final String subjects, final Point lowerLeftPoint, final Point higherRightPoint) {
        Criteria criteria = new Criteria(START).within(new Box(lowerLeftPoint, higherRightPoint));
        return mongoOps.find(new Query(criteria), LocationEntity.class);
    }

    @Override
    public final void addLocations(final String subjects, final List<LocationEntry> entries) {
        List<LocationEntity> entities = new LinkedList<>();
        for (LocationEntry location : entries) {
            final Point locationPoint = new Point(location.getLongitude(), location.getLatitude());
            entities.add(new LocationEntity(subjects, locationPoint));
        }

        this.repository.save(entities);
    }
}
