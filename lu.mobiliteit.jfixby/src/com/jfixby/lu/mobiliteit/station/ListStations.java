
package com.jfixby.lu.mobiliteit.station;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.RedJson;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.json.JsonString;
import com.jfixby.red.desktop.DesktopAssembler;

public class ListStations {

	public static void main (String[] args) throws IOException {
		DesktopAssembler.setup();
		Json.installComponent(new RedJson());

		File stationsFile = LocalFileSystem.ApplicationHome().child("stations.json");
		JsonString json = Json.newJsonString(stationsFile.readToString());
		StationsList stations = Station.extractStations(json);

	}

}
