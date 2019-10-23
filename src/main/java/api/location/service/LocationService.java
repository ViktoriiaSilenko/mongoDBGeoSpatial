package api.location.service;

import api.location.model.LocationEntry;
import api.location.repository.domain.LocationEntity;
import org.springframework.data.mongodb.core.geo.Point;
import java.util.List;

public interface LocationService {
    List<LocationEntity> getLocationsInBox(String subjects, Point lowerLeftPoint, Point higherRightPoint);
    void addLocations(String subjects, List<LocationEntry> entries);
}
