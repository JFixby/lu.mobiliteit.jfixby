
package com.jfixby.lu.mobiliteit.station;

import java.io.IOException;

import com.jfixby.lu.MobiliteitIO;
import com.jfixby.lu.mobiliteit.geo.GeoLocation;
import com.jfixby.scarabei.adopted.gdx.json.GoogleGson;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.json.Json;

public class ListBusesByStation {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		Json.installComponent(new GoogleGson());

		final GeoLocation targetPoint = new GeoLocation(6.1577337, 49.6270327, 18);// @49.6270327,6.1577337,18z

		final List<StationInfo> list = MobiliteitIO.findClosestStation(targetPoint, 5, 0.5d, MobiliteitIO.FROM_FILE);

	}

}
