
package com.jfixby.lu;

import java.io.IOException;

import com.jfixby.lu.mobiliteit.geo.GeoLocation;
import com.jfixby.lu.mobiliteit.station.Station;
import com.jfixby.lu.mobiliteit.station.StationInfo;
import com.jfixby.lu.mobiliteit.station.StationsList;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.net.http.Http;
import com.jfixby.scarabei.api.net.http.HttpCall;
import com.jfixby.scarabei.api.net.http.HttpCallExecutor;
import com.jfixby.scarabei.api.net.http.HttpCallParams;
import com.jfixby.scarabei.api.net.http.HttpCallProgress;
import com.jfixby.scarabei.api.net.http.HttpURL;
import com.jfixby.scarabei.api.util.JUtils;

public class MobiliteitIO {
	static StationsList stations_list;
	static final String PARAM_MAX_DISTANCE_METERS = "PARAM_MAX_DISTANCE_METERS";
	static final String PARAM_LONGTITUDE = "PARAM_LONGTITUDE";
	static final String PARAM_latitude = "PARAM_latitudeE";
	static final String LIST_STATIONS_API_CALL = "http://travelplanner.mobiliteit.lu/hafas/query.exe/dot?performLocating=2&tpl=stop2csv&stationProxy=yes&look_maxdist="
		+ PARAM_MAX_DISTANCE_METERS + "&look_x=" + PARAM_LONGTITUDE + "&look_y=" + PARAM_latitude + "";

	public static final boolean FROM_FILE = true;
	public static final boolean FROM_URL = !FROM_FILE;

	public static List<StationInfo> findClosestStation (GeoLocation targetPoint, int num, double distance, boolean file_flag)
		throws IOException {

		if (stations_list == null) {
			if (file_flag == FROM_FILE) {

				File stationsFile = LocalFileSystem.ApplicationHome().child("stations.json");
				L.d("reading file", stationsFile);
				JsonString json = Json.newJsonString(stationsFile.readToString());
				stations_list = Station.extractStations(json);
			} else {
				HttpCallParams params = Http.newCallParams();
				String meters = "" + (int)(distance * 1000);
				String lat = (long)(targetPoint.getLatitude() * 1000000) + "";
				String longt = (long)(targetPoint.getLongtitude() * 1000000) + "";
				String url_string = LIST_STATIONS_API_CALL.replaceAll(PARAM_MAX_DISTANCE_METERS, meters)
					.replaceAll(PARAM_LONGTITUDE, longt).replaceAll(PARAM_latitude, lat);
				L.d("connecting", url_string);
// L.d("calling", url_string);
				HttpURL http_url = Http.newURL(url_string);
				params.setURL(http_url);
				HttpCall call = Http.newCall(params);
				HttpCallExecutor exe = Http.newCallExecutor();
				HttpCallProgress result = exe.execute(call);
				String result_string = result.readResultAsString("utf-8");
				List<String> parts = JUtils.split(result_string, "; \n");

				StationsList stations = new StationsList();

				Collections.component().convertCollection(parts, stations.list,
					raw_open_data_api_string -> new StationInfo(raw_open_data_api_string));

// stations.print();
				stations_list = stations;

			}
		}
		List<StationInfo> closestStations = stations_list.findClosest(targetPoint, num, distance);
		return closestStations;
	}

}
