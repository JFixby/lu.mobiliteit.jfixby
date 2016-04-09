
package com.jfixby.lu.mobiliteit.station;

import java.util.Comparator;

import com.jfixby.cmns.api.collections.CollectionFilter;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.lu.mobiliteit.geo.GeoLocation;

public class StationsList {

	public final List<StationInfo> list = Collections.newList();

	public void print () {
		list.print("StationsList");
	}

	public List<StationInfo> findClosest (GeoLocation targetPoint, int howMany, double max_distance_km) {
		List<StationInfo> rating_list = Collections.newList();
// rating_list.addAll(list);

		CollectionFilter<StationInfo> filter = point -> point.getLocation().distanceTo(targetPoint) <= max_distance_km;
		rating_list = Collections.filter(list, filter);

		Comparator<? super StationInfo> comparator = new Comparator<StationInfo>() {

			@Override
			public int compare (StationInfo o1, StationInfo o2) {
				GeoLocation g1 = o1.getLocation();
				GeoLocation g2 = o2.getLocation();
				double d1 = g1.distanceTo(targetPoint);
				double d2 = g2.distanceTo(targetPoint);
				return Double.compare(d1, d2);
			}
		};
		;
		rating_list.sort(comparator);

		rating_list.splitAt(howMany);

		return rating_list;

	}

}
