
package com.jfixby.lu.mobiliteit.station;

public class StationInfo {
	public long id;
	public String name;
	public double longitude;
	public double latitude;

	@Override
	public String toString () {
		return "StationInfo [id=" + id + ", name=" + name + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}

}
