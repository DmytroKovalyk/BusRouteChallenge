package org.dmytro.kovalyk;

import java.io.File;
import java.io.FileNotFoundException;

import org.dmytro.kovalyk.exception.ExceptionWithExitCode;
import org.dmytro.kovalyk.util.BusRouteReader;
import org.dmytro.kovalyk.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/** This class is used to load data on start up */
@Component
public class OnStartUpRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnStartUpRunner.class);
    private static final int EXIT_CODE = 3;

    /* @see org.springframework.boot.CommandLineRunner#run(java.lang.String[]) */
    @Override
    public void run(String... args) throws Exception {
        try {
            File dataFile = FileUtil.getFileFromArgs(args);
            BusRouteReader busRouteReader = BusRouteReader.getInstance();
            busRouteReader.setPath(dataFile.toPath());
            busRouteReader.collectBusRoutes();
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new ExceptionWithExitCode(e, EXIT_CODE);
        }
    }

}
