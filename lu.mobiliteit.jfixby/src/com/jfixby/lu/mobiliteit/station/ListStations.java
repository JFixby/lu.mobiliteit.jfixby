
package com.jfixby.lu.mobiliteit.station;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.RedJson;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.lu.MobiliteitIO;
import com.jfixby.lu.mobiliteit.geo.GeoLocation;
import com.jfixby.red.desktop.DesktopSetup;

public class ListStations {

	public static void main (String[] args) throws IOException {
		DesktopSetup.deploy();
		Json.installComponent(new RedJson());

		GeoLocation targetPoint = new GeoLocation(6.1577337, 49.6270327, 18);// @49.6270327,6.1577337,18z
		List<StationInfo> closestStations = MobiliteitIO.findClosestStation(targetPoint, 5, 0.3, MobiliteitIO.FROM_URL);

// stations.print();

		L.d("targetPoint", targetPoint);
		closestStations.print("closest to ");
	}

}
