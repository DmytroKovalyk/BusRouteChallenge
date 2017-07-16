package org.dmytro.kovalyk.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.dmytro.kovalyk.model.BusRoute;
import org.dmytro.kovalyk.model.BusRouteHolder;

/** Singleton helper class to parse bus route data from file */
public final class BusRouteReader {

    private static final String DATA_FILE_SEPARATOR = " ";
    private static final BusRouteReader INSTANCE = new BusRouteReader();
    private Path _path = null;

    private BusRouteReader() {
    }

    /** Simple way to get singleton instance */
    public static BusRouteReader getInstance() {
        return INSTANCE;
    }

    /** The tricky method to set path parameter on start up only one time */
    public void setPath(Path path) {
        if (_path != null) {
            throw new RuntimeException("_path value is setted");
        }
        _path = path;
    }

    /** Collects bus routes from file into {@link BusRouteHolder} */
    public void collectBusRoutes() throws IOException {
        if (_path == null) {
            throw new RuntimeException("_path value is null");
        }
        try (BufferedReader reader = Files.newBufferedReader(_path)) {
            BusRouteHolder.NUMBER_OF_BUS_ROUTES = Integer.parseInt(reader.lines().findFirst().get());
            // skip first line because it is managed
            reader.lines().forEach(line -> {
                String[] ids = line.split(DATA_FILE_SEPARATOR);
                BusRoute busRoute = new BusRoute(ids[0], Arrays.asList(ids).subList(1, ids.length));
                BusRouteHolder.BUS_ROUTES.put(busRoute.getRouteId(), busRoute);
            });
        }
    }
}
