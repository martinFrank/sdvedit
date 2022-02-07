package com.github.martinfrank.sdvedit;


import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class SdvNamePattern {

    public static final String SDV_DIR_NAME = "StardewValley";
    private static final String DIRECTORY_PATTERN = ".*_\\d{9}$";
    private static final Pattern DIRECTORY_MATCHER = Pattern.compile(DIRECTORY_PATTERN);

    public static FilenameFilter filenameFilter(){
        return (dir, filename) ->
                dir.getName().equalsIgnoreCase(SDV_DIR_NAME) && DIRECTORY_MATCHER.matcher(filename).matches();
    }

}
