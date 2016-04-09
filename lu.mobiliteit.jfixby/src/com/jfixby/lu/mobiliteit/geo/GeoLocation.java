
package com.jfixby.lu.mobiliteit.geo;

public class GeoLocation {
	private static final double DEFAULT_ZOOM = 18;
	private static final String id_latitude = "<latitude>";
	private static final String id_longitude = "<longitude>";
	private static final String id_zoom_level = "<zoom level>";
	private static final String GOOGLE_MAPS_STRING_TEMPLATE = "https://www.google.com/maps/preview/@" + id_latitude + ","
		+ id_longitude + "," + id_zoom_level + "z";
	// @49.6270327,6.1577337,18z

//

	final double longitude;
	final double latitude;
	final double zoom;
	final String link;

	public GeoLocation (double longitude, double latitude, double zoom) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.zoom = zoom;
		link = GOOGLE_MAPS_STRING_TEMPLATE.replaceAll(id_latitude, latitude + "").replaceAll(id_longitude, longitude + "")
			.replaceAll(id_zoom_level, zoom + "");
	}

	public GeoLocation (double longitude, double latitude) {
		this(longitude, latitude, DEFAULT_ZOOM);
	}

	@Override
	public String toString () {
		return "GeoLocation [longitude=" + longitude + ", latitude=" + latitude + ", zoom=" + zoom + ", link=" + link + "]";
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(zoom);
		result = prime * result + (int)(temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int)(temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int)(temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		GeoLocation other = (GeoLocation)obj;
		if (Double.doubleToLongBits(zoom) != Double.doubleToLongBits(other.zoom)) return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude)) return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude)) return false;
		return true;
	}

	public double distanceTo (GeoLocation location) {
		return GeoLocation.distance(this, location);
	}

	public static double distance (GeoLocation a, GeoLocation b) {
// return FloatMath.distance(a.attitude, a.latitude, b.attitude, b.latitude);
		return distance(a.latitude, a.longitude, b.latitude, b.longitude, 'K');
	}

	static private double distance (double lat1, double lon1, double lat2, double lon2, char unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
			+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == 'K') {
			dist = dist * 1.609344;
		} else if (unit == 'N') {
			dist = dist * 0.8684;
		}
		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	static private double deg2rad (double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	static private double rad2deg (double rad) {
		return (rad * 180.0 / Math.PI);
	}
// system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "M") + " Miles\n");
// system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "K") + " Kilometers\n");
// system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");

}
