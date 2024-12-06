package com.otdot.hgm.controllers;

import com.otdot.hgm.OkClient;
import com.otdot.hgm.collections.Trip;
import com.otdot.hgm.queries.Queries;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    @Autowired
    OkClient okClient;

    private record TripInput(String gtfsId, String directionId, List<Integer> stopIds, List<Integer> stopTimeIds) {}

    private Response queryDb() throws IOException {
        RequestBody body = RequestBody.create(Queries.mediaType, Queries.STOPSQUERY);
        Request request = new Request.Builder()
                .url(okClient.getApiUrl())
                .post(body)
                .build();

        return OkClient.httpClient.newCall(request).execute();
    }

    @MutationMapping
    public void addTrip(@Argument TripInput tripInput) {
        System.out.println("trip: " + tripInput);
    }

}
