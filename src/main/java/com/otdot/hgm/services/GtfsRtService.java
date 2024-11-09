package com.otdot.hgm.services;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.otdot.hgm.OkClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GtfsRtService {
    @Value("${realtime.hsl.endpoint}")
    private String baseUrl;

    public enum HslGtfsRtEndpoints {
        LOCATIONS("/vehicle-positions/v2/hsl"), UPDATES("/trip-updates/v2/hsl"),

        ALERTS("/service-alerts/v2/hsl");

        String endpoint;

        HslGtfsRtEndpoints(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getEndpoint() {
            return endpoint;
        }
    }

    public FeedMessage getGtfsRtRealTimeData(HslGtfsRtEndpoints endpoint) {

        Request request = new Request.Builder().url(baseUrl + endpoint.getEndpoint()).get().build();

        try {
            okhttp3.Response response = OkClient.httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.format("Failed request to %s", endpoint.getEndpoint()));
            }

            byte[] responseData = response.body().bytes();

            return FeedMessage.parseFrom(responseData);
        } catch (Exception e) {
            System.out.println(String.format("Failed request to %s", endpoint.getEndpoint()));
            e.printStackTrace();
            return null;
        }
    }
}
