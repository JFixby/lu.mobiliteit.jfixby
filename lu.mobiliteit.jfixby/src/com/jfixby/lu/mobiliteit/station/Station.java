
package com.jfixby.lu.mobiliteit.station;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.json.JsonString;

public class Station {

	public static StationsList extractStations (final JsonString json) {
		Err.throwNotImplementedYet();
		return null;
// final StationsList stations = new StationsList();
// final ArrayList<JsonValue> jsonList = Json.deserializeFromString(ArrayList.class, json);
// for (int i = 0; i < jsonList.size(); i++) {
// final JsonValue element = jsonList.get(i);
//// L.d("element", element);
// final String rawString = element.toString();
// final JsonString stationJson = Json.newJsonString(rawString);
// final StationInfo stationInfo = Json.deserializeFromString(StationInfo.class, stationJson);
// stations.list.add(stationInfo);
// }
//
//// List<String> stringList = Collections.newList(jsonList);
// return stations;
	}

}
