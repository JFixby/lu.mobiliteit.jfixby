
package com.jfixby.lu.mobiliteit.station;

import java.util.ArrayList;

import com.jfixby.scarabei.adopted.gdx.io.JsonValue;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;

public class Station {

	public static StationsList extractStations (JsonString json) {
		StationsList stations = new StationsList();
		ArrayList<JsonValue> jsonList = Json.deserializeFromString(ArrayList.class, json);
		for (int i = 0; i < jsonList.size(); i++) {
			JsonValue element = jsonList.get(i);
// L.d("element", element);
			String rawString = element.toString();
			JsonString stationJson = Json.newJsonString(rawString);
			StationInfo stationInfo = Json.deserializeFromString(StationInfo.class, stationJson);
			stations.list.add(stationInfo);
		}

// List<String> stringList = Collections.newList(jsonList);
		return stations;
	}

}
