package org.dmytro.kovalyk.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Contains information about bus route */
public class BusRoute {

    private final String _routeId;
    /** {@link Set} is designed as hash table data structure
     * so complexity is around O(1) to find some value in this structure */
    private final Set<String> _stationsSids;

    public BusRoute(String routeId, List<String> stationsIds) {
        _routeId = routeId;
        // there is no requirements about order of data
        // if you want to save order of station ids then use {@link LinkedHashSet}
        _stationsSids = new HashSet<>(stationsIds);
    }

    /** @return route id */
    public String getRouteId() {
        return _routeId;
    }

    /** @return {@link Set} of station sids */
    public Set<String> getStationsSids() {
        return Collections.unmodifiableSet(_stationsSids);
    }

    /** @return true if bus route has given station sid */
    public boolean hasStationSid(String stationsSid) {
        return _stationsSids.contains(stationsSid);
    }
}
