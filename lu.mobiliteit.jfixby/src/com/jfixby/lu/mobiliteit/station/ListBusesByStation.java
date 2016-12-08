
package com.jfixby.lu.mobiliteit.station;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.RedJson;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.lu.MobiliteitIO;
import com.jfixby.lu.mobiliteit.geo.GeoLocation;

public class ListBusesByStation {

	public static void main (String[] args) throws IOException {
		DesktopSetup.deploy();
		Json.installComponent(new RedJson());

		GeoLocation targetPoint = new GeoLocation(6.1577337, 49.6270327, 18);// @49.6270327,6.1577337,18z

		List<StationInfo> list = MobiliteitIO.findClosestStation(targetPoint, 5, 0.5d, MobiliteitIO.FROM_FILE);

	}

}
