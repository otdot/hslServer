package com.otdot.hgm;

import lombok.Data;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HslApi {

    private static final RestTemplateBuilder restTemplate = new RestTemplateBuilder();

    public record Query(String query) {}

    public static List<Stop> allStops() {
        String url = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql?digitransit-subscription-key=f0dbf2b5a93647b5ba6610ed150a71f5";
        String requestJson = "{\"query\": \"{ stops(feeds: \"HSL\") { id name lat lon }}\"}";
        Query object = new Query("{stops(feeds: \"HSL\") { id name lat lon }}}");
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        StopResponse response = restTemplate.build().postForObject(url, object, StopResponse.class);
        System.out.println(response);
        return response.getData();
    }

    @Data
    public static class StopResponse {
        private List<Stop> data;
    }
}


//"{\n" +
//        "stops(feeds: \"HSL\") {\n" +
//        "      id \n" +
//        "      name \n" +
//        "      lat \n" +
//        "      lon \n" +
//        "  }\n" +
//        "}";
