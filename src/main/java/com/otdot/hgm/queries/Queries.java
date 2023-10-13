package com.otdot.hgm.queries;

import okhttp3.MediaType;
import org.springframework.stereotype.Component;

@Component
public record Queries() {

    public static MediaType mediaType = MediaType.parse("application/graphql");
    public static String STOPQUERY = "{\n" +
            "  stops {\n" +
            "    id\n" +
            "    name\n" +
            "    lat\n" +
            "    lon\n" +
            "  }\n" +
            "}";

}
