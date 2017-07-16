package org.dmytro.kovalyk.model;

import org.dmytro.kovalyk.util.UrlConstants;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to show a state whether there is at least one bus route which has direct connection between the
 * ids of stations
 */
public class DirectionState {

    private final int _depSid;
    private final int _arrSid;
    private final boolean _directBusRoute;

    public DirectionState(String depSid, String arrSid, boolean directBusRoute) {
        _depSid = Integer.parseInt(depSid);
        _arrSid = Integer.parseInt(arrSid);
        _directBusRoute = directBusRoute;
    }

    /** @return departure sid */
    @JsonProperty(value = UrlConstants.DEPARTURE_SID)
    public int getDepSid() {
        return _depSid;
    }

    /** @return arrival sid */
    @JsonProperty(value = UrlConstants.ARRIVAL_SID)
    public int getArrSid() {
        return _arrSid;
    }

    /** @return true if there is direct bus route otherwise false */
    @JsonProperty(value = UrlConstants.DIRECT_BUS_ROUTE)
    public boolean isDirectBusRoute() {
        return _directBusRoute;
    }

    @Override
    public String toString() {
        return "DirectionState [" + UrlConstants.ARRIVAL_SID + "=" + _depSid + ", " + UrlConstants.ARRIVAL_SID
                + "=" + _arrSid + ", " + UrlConstants.DIRECT_BUS_ROUTE + "=" + _directBusRoute + "]";
    }

}
