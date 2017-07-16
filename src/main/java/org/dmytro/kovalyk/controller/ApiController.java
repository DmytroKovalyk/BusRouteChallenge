package org.dmytro.kovalyk.controller;

import javax.annotation.Resource;

import org.dmytro.kovalyk.model.DirectionState;
import org.dmytro.kovalyk.service.BusRouteService;
import org.dmytro.kovalyk.util.UrlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** REST-API */
@RestController
public class ApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Resource
    private BusRouteService _busRouteService;

    @RequestMapping(UrlConstants.API_DIRECT_URL)
    public DirectionState direct(
            @RequestParam(value = UrlConstants.DEPARTURE_SID, defaultValue = "-1") String depSid,
            @RequestParam(value = UrlConstants.ARRIVAL_SID, defaultValue = "-1") String arrSid) {

        DirectionState directionState =
                new DirectionState(depSid, arrSid, _busRouteService.isDirectBusRoute(depSid, arrSid));
        LOGGER.info(directionState.toString());
        return directionState;
    }
}
