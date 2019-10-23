package api.location.repository.domain;

import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locations")
public class LocationEntity {

  private String id;
  private String subject;
  private Point location;

  public LocationEntity(final String subject, final Point location) {
    this.subject = subject;
    this.location = location;
  }

  public String getId() {
    return this.id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getSubject() {
    return this.subject;
  }

  public void setSubject(final String subject) {
    this.subject = subject;
  }

  public Point getLocation() {
    return this.location;
  }

  public void setLocation(final Point location) {
    this.location = location;
  }

}
