package api.location.exceptions;

public class LocationServiceException extends IllegalArgumentException {
    public LocationServiceException(String message) {
        super(message);
    }
}
