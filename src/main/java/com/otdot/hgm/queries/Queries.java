package com.otdot.hgm.queries;

import okhttp3.MediaType;
import org.springframework.stereotype.Component;

@Component
public record Queries() {

    public static MediaType mediaType = MediaType.parse("application/graphql");
    public static String STOPSQUERY = "{\n" +
            "  stops {\n" +
            "    gtfsId\n" +
            "    name\n" +
            "    lat\n" +
            "    lon\n" +
            "  }\n" +
            "}";

    public static String STOPQUERY(String id) {
        return "{\n" +
                "  stop (id:\"" + id + "\") {\n" +
                "    gtfsId\n" +
                "    name\n" +
                "    lat\n" +
                "    lon\n" +
                "  }\n" +
                "}";
    }

    public static String TRIPSQUERY = "{\n" +
                "  trips {\n" +
                "    gtfsId\n" +
                "    directionId\n" +
                "  }\n" +
                "}";
}
