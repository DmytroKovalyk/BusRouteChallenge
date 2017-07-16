package org.dmytro.kovalyk.util;

import java.io.File;
import java.io.FileNotFoundException;

/** Helpful class to handle files */
public class FileUtil {

    /** 
     * @param args - Actually command line arguments
     * 
     * @return {@link File} instance of first argument
     * @throws FileNotFoundException - if file name doesn't exist or isn't simple text file
     */
    public static File getFileFromArgs(String... args) throws FileNotFoundException {
        if (args.length == 0) {
            throw new FileNotFoundException("Sorry, you didn't enter input file name");
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            throw new FileNotFoundException("Sorry, your entered file doesn't exist");
        }

        if (file.isDirectory()) {
            throw new FileNotFoundException("Sorry, your entered file is directory");
        }
        return file;
    }

}
