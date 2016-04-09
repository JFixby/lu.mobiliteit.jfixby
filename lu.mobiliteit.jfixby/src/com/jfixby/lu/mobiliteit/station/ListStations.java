
package com.jfixby.lu.mobiliteit.station;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.RedJson;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.json.JsonString;
import com.jfixby.cmns.api.log.L;
import com.jfixby.lu.mobiliteit.geo.GeoLocation;
import com.jfixby.red.desktop.DesktopAssembler;

public class ListStations {

	public static void main (String[] args) throws IOException {
		DesktopAssembler.setup();
		Json.installComponent(new RedJson());

		File stationsFile = LocalFileSystem.ApplicationHome().child("stations.json");
		JsonString json = Json.newJsonString(stationsFile.readToString());
		StationsList stations = Station.extractStations(json);
// stations.print();

		GeoLocation targetPoint = new GeoLocation(6.1577337, 49.6270327, 18);// @49.6270327,6.1577337,18z

		List<StationInfo> closestStations = stations.findClosest(targetPoint, 5, 0.3);
		L.d("targetPoint", targetPoint);
		closestStations.print("closest to ");
	}

}
