package org.dmytro.kovalyk.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** This class is responsible for holding bus routes data */
public class BusRouteHolder {

    public static final Map<String, BusRoute> BUS_ROUTES = new HashMap<>();
    public static int NUMBER_OF_BUS_ROUTES;

    /**
     * @return true if at least one bus route has direct connection between departure and arrival stations
     *         otherwise false
     */
    public static final boolean isDirectBusRoute(String depSid, String arrSid) {
        Optional<BusRoute> optional = BUS_ROUTES.values().parallelStream()
                .filter(busRoute -> busRoute.hasStationSid(arrSid) && busRoute.hasStationSid(depSid)).findAny();
        return optional.isPresent();
    }
}
