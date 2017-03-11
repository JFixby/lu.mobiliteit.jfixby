
package com.jfixby.lu.mobiliteit.station;

import java.io.IOException;

import com.jfixby.lu.MobiliteitIO;
import com.jfixby.lu.mobiliteit.geo.GeoLocation;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.gson.GoogleGson;

public class ListStations {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		Json.installComponent(new GoogleGson());

		final GeoLocation targetPoint = new GeoLocation(6.1577337, 49.6270327, 18);// @49.6270327,6.1577337,18z
		final List<StationInfo> closestStations = MobiliteitIO.findClosestStation(targetPoint, 5, 0.3, MobiliteitIO.FROM_URL);

// stations.print();

		L.d("targetPoint", targetPoint);
		closestStations.print("closest to ");
	}

}
