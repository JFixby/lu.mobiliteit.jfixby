
package com.jfixby.lu.mobiliteit.station;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.lu.mobiliteit.geo.GeoLocation;

public class StationInfo {
	private String id;
	private String name;
	private double longitude;
	private double latitude;
	private GeoLocation location = null;
	private String raw_open_data_api_string;

	public StationInfo () {
	}

	public StationInfo (String raw_open_data_api_string) {
		this.raw_open_data_api_string = raw_open_data_api_string;
		List<String> pars = JUtils.split(raw_open_data_api_string, "@");
// pars.print(raw_open_data_api_string);
		this.name = readParam("O", pars);
// this.id = readParam("O", pars);
		this.longitude = Double.parseDouble(readParam("X", pars).replaceAll(",", "."));
		this.latitude = Double.parseDouble(readParam("Y", pars).replaceAll(",", "."));
	}

	private String readParam (String paramName, List<String> pars) {
		final String prefix = paramName + "=";
		return pars.filter(string -> string.startsWith(prefix)).getLast().replaceAll(prefix, "");
	}

	@Override
	public String toString () {
		return "StationInfo [name=" + name + ", location=" + getLocation() + "]";
	}

	public GeoLocation getLocation () {
		if (this.location == null) {
			this.location = new GeoLocation(longitude, latitude);
		}
		return location;
	}

}
