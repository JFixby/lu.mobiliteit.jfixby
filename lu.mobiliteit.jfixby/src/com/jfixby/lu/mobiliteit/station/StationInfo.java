
package com.jfixby.lu.mobiliteit.station;

import com.jfixby.lu.mobiliteit.geo.GeoLocation;

public class StationInfo {
	public long id;
	public String name;
	private double longitude;
	private double latitude;
	private GeoLocation location = null;

	@Override
	public String toString () {
		return "StationInfo [id=" + id + ", name=" + name + ", location=" + location + "]";
	}

	public GeoLocation getLocation () {
		if (this.location == null) {
			this.location = new GeoLocation(longitude, latitude);
		}
		return location;
	}

}
