package org.dmytro.kovalyk.service;

import org.dmytro.kovalyk.model.BusRouteHolder;
import org.springframework.stereotype.Service;

/** Helpful bus route Service */
@Service
public class BusRouteService {

    /**
     * @return true if at least one bus route has direct connection between departure and arrival stations
     *         otherwise false
     */
    public boolean isDirectBusRoute(String depSid, String arrSid) {
        return BusRouteHolder.isDirectBusRoute(depSid, arrSid);
    }
}
