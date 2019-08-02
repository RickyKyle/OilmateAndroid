package com.rickykyle.oilmate.utilities;

import com.rickykyle.oilmate.network.responses.Reading;

import java.util.List;

/*
 * Contains values which are repeatedly accessed throughout the application
 * after being generated via a network call.
 */
public class Globals {

    // Makes the list of readings globally available.
    public static List<Reading> readings;

    public static int tankSize = 20;

    // Makes the userID globally available.
    public static int userID;

    // Makes the JWT token for HTTP requests globally available.
    public static String token;
}
