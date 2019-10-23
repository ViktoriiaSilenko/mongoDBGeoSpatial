package api.location.controller;

import api.location.exceptions.LocationServiceException;
import api.location.model.LocationEntry;
import api.location.repository.domain.LocationEntity;
import api.location.service.LocationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public final List<LocationEntity> getLocations(
            @RequestParam("lowerLatitude") double lowerLatitude,
            @RequestParam("lowerLongitude") double lowerLongitude,
            @RequestParam("higherLatitude") double higherLatitude,
            @RequestParam("higherLongitude") double higherLongitude,
            @RequestParam(value = "subjects", required = false) String subjects) throws LocationServiceException {
        validateCoordinates(lowerLatitude, lowerLongitude, higherLatitude, higherLongitude);
        validateSubjects(subjects);

        return this.locationService.getLocationsInBox(subjects,
                new Point(lowerLongitude, lowerLatitude),
                new Point(higherLongitude, higherLatitude));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public final void addLocations(
            @RequestParam("subjects") String subjects,
            @RequestBody List<LocationEntry> entries) {
        validateSubjects(subjects);
        validateEntries(entries);
        this.locationService.addLocations(subjects, entries);
    }

    private void validateSubjects(String subjects) {
        if (StringUtils.isBlank(subjects)) {
            throw new LocationServiceException("Subjects should be not blank");
        }
    }

    private void validateEntries(List<LocationEntry> entries) {
        if (isEmpty(entries)) {
            throw new LocationServiceException("Entries for adding should not be empty");
        }
    }

    private void validateCoordinates(double lowerLatitude, double lowerLongitude,
                                     double higherLatitude, double higherLongitude) {
        if (lowerLatitude > higherLatitude) {
            String error = String.join(" ", "lowerLatitude =", String.valueOf(lowerLatitude), "should be less than higherLatitude =", String.valueOf(higherLatitude));
            throw new LocationServiceException(error);
        }
        if (lowerLongitude > higherLongitude) {
            String error = String.join(" ", "lowerLongitude =", String.valueOf(lowerLongitude), "should be less than higherLongitude =", String.valueOf(higherLongitude));
            throw new LocationServiceException(error);
        }
    }

}
