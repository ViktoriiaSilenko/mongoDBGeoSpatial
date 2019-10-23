package api.location.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.io.Serializable;

public class LocationEntry implements Serializable {

  private double longitude;
  private double latitude;

  public double getLongitude() {
    return this.longitude;
  }

  public void setLongitude(final double longitude) {
    this.longitude = longitude;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public void setLatitude(final double latitude) {
    this.latitude = latitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof LocationEntry)) return false;

    LocationEntry that = (LocationEntry) o;

    return new EqualsBuilder()
            .append(longitude, that.longitude)
            .append(latitude, that.latitude)
            .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
            .append(longitude)
            .append(latitude)
            .toHashCode();
  }

}
